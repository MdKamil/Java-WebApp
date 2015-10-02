package com.practice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.practice.domain.ChangePassword;
import com.practice.domain.ChangePasswordResult;
import com.practice.domain.User;
import com.practice.service.UserService;
import com.practice.validator.UserValidator;

@Controller
@SessionAttributes("user")
public class Login_Signup_Controller {

	@Autowired
	private UserValidator validator;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginForm(Model model) {
		return new ModelAndView("login", "command", new User());
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView getSignupForm() {
		return new ModelAndView("signup", "user", new User());
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, ModelMap model, HttpServletRequest request) {
		validator.validate(user, result);
		if (result.hasErrors()) {
			model.addAttribute("command", user);
			return "signup";
		} else {
			if (userService.checkUserExists(user)) {
				model.addAttribute("emailAlreadyExistsError",
						"Someone already has that email Id.Try another?");
				model.addAttribute("command", user);
				return "signup";
			} else {
				createUser(user);
				authenticateUserAndSetSession(user);
				return "redirect:/home";
			}
		}
	}

	private void createUser(User user) {
		userService.createUser(user);
	}

	private void authenticateUserAndSetSession(User user) {
		try {
			List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
			AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
			user.clearPassword();
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					user, null, AUTHORITIES);
			SecurityContextHolder.getContext()
					.setAuthentication(authentication);
		} catch (AuthenticationException exception) {
			System.out
					.println("Exception thrown while creating session for user");
		}
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	@ResponseBody
	public ChangePasswordResult changePassword(
			@ModelAttribute("changePassword") ChangePassword changePassword,
			Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		if (changePassword.getOldPassword().equals(
				changePassword.getNewPassword())) {
			ChangePasswordResult result = new ChangePasswordResult();
			result.setMessage("Password must differ from old password.");
			result.setResult("FAILURE");
			return result;
		} else {
			userService.updatePassword(user, changePassword.getNewPassword());
			ChangePasswordResult result = new ChangePasswordResult();
			result.setMessage("Password change is successfull");
			result.setResult("SUCCESS");
			return result;
		}
	}
}

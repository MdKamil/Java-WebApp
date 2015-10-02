package com.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.practice.domain.User;
import com.practice.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		User user = userService.retrieveUserByEmail(authentication
				.getPrincipal().toString());
		if (user == null) {
			throw new BadCredentialsException(
					"The email you entered doesn't exist.");
		} else {
			String targetPassword = user.getPassword();
			CharSequence rawCandidatePassword = authentication.getCredentials()
					.toString();
			if (encoder.matches(rawCandidatePassword, targetPassword)) {
				List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
				AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
				user.clearPassword();
				authentication = new UsernamePasswordAuthenticationToken(user,
						null, AUTHORITIES);
				return authentication;
			} else {
				throw new BadCredentialsException(
						"The email and password you entered don't match.");
			}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}

}

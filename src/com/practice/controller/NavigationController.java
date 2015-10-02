package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.domain.ChangePassword;
import com.practice.domain.Task;
import com.practice.domain.ToDo;
import com.practice.domain.User;
import com.practice.service.CrudService;

@Controller
public class NavigationController {

	@Autowired
	private CrudService crudService;

	private static long todocountforpagination = 0;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView geHomePage(Model model) {
		todocountforpagination = 0;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		List<ToDo> allToDo = crudService.getTodoList(user.getId());
		Long todoCount = crudService.getTotalTodoCount(user.getId());
		model.addAttribute("todolist", allToDo);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("todoCount", todoCount);
		return new ModelAndView("home", "command", new ToDo());
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String getSettingsPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		model.addAttribute("user", user);
		model.addAttribute("username", user.getUsername());
		model.addAttribute("changePassword", new ChangePassword());
		return "settings";
	}

	@RequestMapping(value = "/viewTodo/{todoId}", method = RequestMethod.GET)
	public ModelAndView getToDoViewPage(@PathVariable("todoId") Integer todoId,
			ModelMap modelMap) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		ToDo todo = crudService.getToDoById(todoId);
		List<Task> allTaskOfToDo = crudService.getAllTask(todoId);
		modelMap.put("todo", todo);
		modelMap.put("tasklist", allTaskOfToDo);
		modelMap.addAttribute("todo", todo);
		modelMap.addAttribute("task", new Task());
		modelMap.addAttribute("createtask", new Task());
		modelMap.addAttribute("username", user.getUsername());
		return new ModelAndView("viewtodo");
	}

	@RequestMapping(value = "/gettodo", method = RequestMethod.GET)
	public ModelAndView retrieveTodoByPagination(
			@RequestParam(value = "page") String page, Model model) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) auth.getPrincipal();
		if (page.equalsIgnoreCase("next")) {
			todocountforpagination += 8;
			List<ToDo> alltodo = crudService.getTodoForPagination(
					todocountforpagination, user.getId());
			model.addAttribute("todolist", alltodo);
		} else if (page.equalsIgnoreCase("previous")) {
			todocountforpagination -= 8;
			List<ToDo> alltodo = crudService.getTodoForPagination(
					todocountforpagination, user.getId());
			model.addAttribute("todolist", alltodo);
		}
		return new ModelAndView("subViews/alltodo");
	}

}

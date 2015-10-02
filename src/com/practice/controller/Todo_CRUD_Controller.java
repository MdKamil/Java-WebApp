package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.practice.domain.Task;
import com.practice.domain.ToDo;
import com.practice.domain.User;
import com.practice.service.CrudService;

@Controller
public class Todo_CRUD_Controller {

	@Autowired
	CrudService crudService;

	@RequestMapping(value = "/createTodo", method = RequestMethod.POST)
	public ModelAndView createTodo(@ModelAttribute("toDo") ToDo toDo,
			Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) authentication.getPrincipal();
		toDo.setUser(user);
		crudService.createTodo(toDo);
		List<ToDo> allToDo = crudService.getTodoList(user.getId());
		model.addAttribute("todolist", allToDo);
		Long todoCount = crudService.getTotalTodoCount(user.getId());
		model.addAttribute("todoCount", todoCount);
		return new ModelAndView("subViews/alltodo");
	}

	@RequestMapping(value = "/deletetodo/{todoId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteTodoAjaxCall(@PathVariable Integer todoId) {
		crudService.deleteTodo(todoId);
	}

	@RequestMapping(value = "/deletetodo/{todoId}", method = RequestMethod.GET)
	public String deleteTodoGetCall(@PathVariable Integer todoId) {
		crudService.deleteTodo(todoId);
		return "redirect:/home";
	}

	@RequestMapping(value = "/getAllTodo", method = RequestMethod.GET)
	public ModelAndView getAllTodo(Model model) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		User user = (User) authentication.getPrincipal();
		List<ToDo> allToDo = crudService.getTodoList(user.getId());
		model.addAttribute("todolist", allToDo);
		Long todoCount = crudService.getTotalTodoCount(user.getId());
		model.addAttribute("todoCount", todoCount);
		return new ModelAndView("subViews/alltodo");
	}

	@RequestMapping(value = "/createtask/{todoId}", method = RequestMethod.POST)
	public ModelAndView createTask(@ModelAttribute("task") Task task,
			Model model, @PathVariable Integer todoId) {
		ToDo todo = crudService.getToDoById(todoId);
		task.setToDo(todo);
		crudService.createTask(task);
		List<Task> alltaskOfToDO = crudService.getAllTask(todoId);
		model.addAttribute("tasklist", alltaskOfToDO);
		model.addAttribute("createtask", new Task());
		model.addAttribute("todo", todo);
		return new ModelAndView("subViews/taskviewer");
	}

	@RequestMapping(value = "/deletetask/todo/{todoId}/task/{taskId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteTask(@PathVariable int taskId,
			@PathVariable Integer todoId, Model model) {
		crudService.deleteTask(taskId);
	}

	@RequestMapping(value = "/gellAllTasks/todo/{todoId}", method = RequestMethod.GET)
	public ModelAndView getAllTasks(Model model, @PathVariable Integer todoId) {
		List<Task> alltaskOfToDO = crudService.getAllTask(todoId);
		model.addAttribute("tasklist", alltaskOfToDO);
		model.addAttribute("createtask", new Task());
		ToDo todo = crudService.getToDoById(todoId);
		model.addAttribute("todo", todo);
		return new ModelAndView("subViews/taskviewer");
	}

}

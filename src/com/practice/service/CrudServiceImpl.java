package com.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.dao.CrudDAO;
import com.practice.domain.Task;
import com.practice.domain.ToDo;

@Service
public class CrudServiceImpl implements CrudService {

	@Autowired
	CrudDAO crudDAO;

	@Transactional
	@Override
	public void createTodo(ToDo toDo) {
		crudDAO.createToDO(toDo);
	}

	@Transactional
	@Override
	public void deleteTodo(Integer todoId) {
		crudDAO.deleteToDo(todoId);
	}

	@Transactional
	@Override
	public List<ToDo> getTodoList(int user_id) {
		return crudDAO.listToDo(user_id);
	}

	@Transactional
	@Override
	public void createTask(Task task) {
		crudDAO.createTask(task);
	}

	@Transactional
	@Override
	public void deleteTask(int taskId) {
		crudDAO.deleteTask(taskId);
	}

	@Transactional
	@Override
	public ToDo getToDoById(Integer todoId) {
		return crudDAO.getToDoById(todoId);
	}

	@Transactional
	@Override
	public List<Task> getAllTask(Integer todoId) {
		return crudDAO.listTask(todoId);
	}

	@Transactional
	@Override
	public List<ToDo> getTodoForPagination(Long startindex, Integer user_id) {
		return crudDAO.getTodoForPagination(startindex, user_id);
	}

	@Transactional
	@Override
	public Long getTotalTodoCount(int user_id) {
		return crudDAO.getTotalTodoCount(user_id);
	}
}

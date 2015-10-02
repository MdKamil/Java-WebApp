package com.practice.service;

import java.util.List;

import com.practice.domain.Task;
import com.practice.domain.ToDo;

public interface CrudService {

	public void createTodo(ToDo toDo);

	public ToDo getToDoById(Integer todoId);

	public void deleteTodo(Integer todoId);

	public Long getTotalTodoCount(int user_id);

	public List<ToDo> getTodoForPagination(Long startindex, Integer user_id);

	public List<ToDo> getTodoList(int user_id);

	public void createTask(Task task);

	public void deleteTask(int taskId);

	public List<Task> getAllTask(Integer todoId);

}

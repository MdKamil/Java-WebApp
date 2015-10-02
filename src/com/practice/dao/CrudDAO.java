package com.practice.dao;

import java.util.List;

import com.practice.domain.Task;
import com.practice.domain.ToDo;

public interface CrudDAO {

	public void createToDO(ToDo toDo);

	public ToDo getToDoById(Integer todoId);

	public void deleteToDo(Integer todoId);

	public List<ToDo> listToDo(int user_id);

	public Long getTotalTodoCount(int user_id);

	public List<ToDo> getTodoForPagination(Long startindex, Integer user_id);

	public void createTask(Task task);

	public void deleteTask(int taskId);

	public List<Task> listTask(Integer todoId);

}

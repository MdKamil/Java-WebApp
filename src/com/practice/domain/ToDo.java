package com.practice.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TODO")
public class ToDo {

	@Id
	@GeneratedValue
	@Column(name = "TODO_ID")
	private int todoId;

	@Column(name = "TODO_TITLE")
	private String todoTitle;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	@OneToMany(mappedBy = "todo")
	private Set<Task> listOfTask = new HashSet<Task>();

	@Column(name = "DATE_CREATED")
	private Date todoCreatedDate = new Date();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Task> getListOfTask() {
		return listOfTask;
	}

	public void setListOfTask(Set<Task> listOfTask) {
		this.listOfTask = listOfTask;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public Date getTodoCreatedDate() {
		return todoCreatedDate;
	}

	public void setTodoCreatedDate(Date todoCreatedDate) {
		this.todoCreatedDate = todoCreatedDate;
	}

}

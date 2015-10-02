package com.practice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practice.domain.Task;
import com.practice.domain.ToDo;

@Repository
public class CrudDAOImpl implements CrudDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createToDO(ToDo toDo) {
		sessionFactory.getCurrentSession().save(toDo);
	}

	@Override
	public void deleteToDo(Integer todoId) {
		/*
		 * ToDo toDo = (ToDo)
		 * sessionFactory.getCurrentSession().load(ToDo.class, todoId);
		 */
		ToDo toDo = (ToDo) sessionFactory.getCurrentSession()
				.createQuery("from ToDo where TODO_ID=:id")
				.setInteger("id", todoId).uniqueResult();
		if (toDo != null) {
			sessionFactory.getCurrentSession().delete(toDo);
		}
	}

	@Override
	public ToDo getToDoById(Integer todoId) {
		ToDo toDo = (ToDo) sessionFactory.getCurrentSession()
				.createQuery("from ToDo where TODO_ID=:id")
				.setInteger("id", todoId).uniqueResult();
		System.out.println(toDo);
		if (toDo != null) {
			return toDo;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ToDo> listToDo(int user_id) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from ToDo where USER_ID=:id order by todoCreatedDate desc");
		query.setInteger("id", user_id);
		query.setMaxResults(8);
		List<ToDo> todoList = (List<ToDo>) query.list();
		return todoList;
	}

	@Override
	public void createTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	@Override
	public void deleteTask(int taskId) {
		Task task = (Task) sessionFactory.getCurrentSession()
				.createQuery("from Task where taskId=:id")
				.setInteger("id", taskId).uniqueResult();
		if (task != null) {
			sessionFactory.getCurrentSession().delete(task);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> listTask(Integer todoId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Task where TODO_ID=:id");
		query.setInteger("id", todoId);
		List<Task> todoTasks = (List<Task>) query.list();
		return todoTasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ToDo> getTodoForPagination(Long startindex, Integer user_id) {
		int id = user_id;
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ToDo.class).add(Restrictions.eq("user.id", id))
				.addOrder(Order.desc("todoCreatedDate"));
		Integer integer = startindex.intValue();
		criteria.setFirstResult(integer);
		criteria.setMaxResults(8);
		return (List<ToDo>) criteria.list();
	}

	@Override
	public Long getTotalTodoCount(int user_id) {
		Long todoCount = (Long) sessionFactory.getCurrentSession()
				.createCriteria(ToDo.class)
				.add(Restrictions.eq("user.id", user_id))
				.setProjection(Projections.rowCount()).uniqueResult();
		return todoCount;
	}
}

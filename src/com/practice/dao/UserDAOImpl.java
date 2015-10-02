package com.practice.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.practice.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void editUser() {

	}

	@Override
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean checkUserExists(User user) {
		System.out.println("checking for user");
		User retrievedUserByEmail = (User) sessionFactory.getCurrentSession()
				.createQuery("from User where USER_EMAIL = ?")
				.setString(0, user.getEmail()).uniqueResult();
		if (retrievedUserByEmail == null) {
			return false;
		}
		return true;
	}

	@Override
	public User retrieveUserByEmail(String user) {
		User retrievedUser = (User) sessionFactory.getCurrentSession()
				.createQuery("from User where USER_EMAIL = ?")
				.setString(0, user).uniqueResult();
		if (retrievedUser == null) {
			return null;
		} else {
			return retrievedUser;
		}
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		sessionFactory.getCurrentSession().update(user);
	}
}

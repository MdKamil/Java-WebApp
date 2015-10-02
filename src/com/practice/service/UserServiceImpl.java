package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.dao.UserDAO;
import com.practice.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public void editUser() {
		userDAO.editUser();
	}

	@Transactional
	@Override
	public void createUser(User user) {
		userDAO.createUser(user);
	}

	@Transactional
	@Override
	public boolean checkUserExists(User user) {
		return userDAO.checkUserExists(user);
	}

	@Transactional
	@Override
	public User retrieveUserByEmail(String user) {
		return userDAO.retrieveUserByEmail(user);
	}

	@Transactional
	@Override
	public void updatePassword(User user, String newPassword) {
		userDAO.updatePassword(user, newPassword);
	}

}

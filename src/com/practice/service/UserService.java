package com.practice.service;

import com.practice.domain.User;

public interface UserService {

	public void createUser(User user);

	public void editUser();

	public boolean checkUserExists(User user);

	public User retrieveUserByEmail(String user);

	public void updatePassword(User user,String newPassword);
}

package com.revature.project_0.JDBC;

import java.util.List;

import com.revature.project_0.User;

public interface UserDAO {
	
	User getUser(int id);
	User getUser(String username);
	List<User> getAllUsers();
	boolean insertUser(User user);
	boolean approveUser(int id);
	boolean denyUser(int id);
	boolean lockUser(int id);
	boolean unlockUser(int id);
	boolean deposit(int id, int deposit);
	boolean withdraw(int id, int withdrawal);
	boolean deleteUser(int id);
	String getPasswordHash(User user);
	boolean checkForAdmin();
	boolean promoteAdmin(int id);
}

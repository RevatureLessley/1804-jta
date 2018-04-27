package com.revature.dao.users;

import java.util.ArrayList;


import com.revature.users.Admin;


public interface AdminDao {

	public boolean addAdmin(Admin admin);
	
	public ArrayList<Admin> getAdmins();

	public Admin getAdmin(String username);
}
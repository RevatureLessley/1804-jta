package com.bank.factory;

import java.util.Scanner;

import com.bank.exception.UserNotFoundException;
import com.bank.mainmenu.MainMenu;
import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.singleton.LogSingleton;

public class AdminLogin {

	public static void adminLogin() throws UserNotFoundException{
		LogSingleton.getInstance();			
			System.out.println("____Welcome to the admin login!____");	
			System.out.println("1. Approve an account");
			System.out.println("2. Lock an account");
			System.out.println("3. Unlock an account");
			System.out.println("4. Exit");
	
			try(Scanner scan = new Scanner(System.in)){
				int adminOption = scan.nextInt();
				
				switch(adminOption) {
				
				case 1:
					System.out.println(UserService.getUsers());
					System.out.println("Please enter the first name");
					String fname = scan.next();
					System.out.println("Please enter the last name");
					String lname = scan.next();
					UserService.getApproved(UserService.getUser(fname, lname));
					System.out.println("\n");
					
					break;
				case 2:
					System.out.println(UserService.getUsers());
					System.out.println("Please enter the first name");
					String f_name = scan.next();
					System.out.println("Please enter the last name");
					String l_name = scan.next();
					UserService.getLocked(UserService.getUser(f_name, l_name));
					System.out.println("\n");
					
					break;
					
				case 3:
					System.out.println(UserService.getUsers());
					System.out.println("Please enter the first name");
					String fi_name = scan.next();
					System.out.println("Please enter the last name");
					String la_name = scan.next();
					UserService.getLocked(UserService.getUser(fi_name, la_name));
					System.out.println("\n");
					break;
				
				case 4:
					System.exit(0);
				}
				MainMenu.startMenu();
			}
	}
}
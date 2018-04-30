package com.bank.mainmenu;
import java.util.Scanner;

import com.bank.exception.IncorrectInputException;
import com.bank.exception.UserNotFoundException;
import com.bank.factory.AdminLogin;
import com.bank.factory.SignUp;
import com.bank.factory.UserLogin;
import com.bank.model.User;
import com.bank.service.UserService;
import com.bank.singleton.LogSingleton;

public class MainMenu {

	public static void startMenu() throws UserNotFoundException {
		LogSingleton.getInstance();
		System.out.println("Welcome!");
		System.out.println("Please select an option by number: ");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		
		
		try (Scanner scan = new Scanner(System.in))
		{
			int mainOption = scan.nextInt();
			if (mainOption == 1){
				System.out.println("Sign in as: ");
				System.out.println("1: Admin");
				System.out.println("2. User");
				
				int loginAs = scan.nextInt();
				
				switch(loginAs) {
				case 1:
					System.out.println("What is your first name?");
					String firstName = scan.next();
					System.out.println("What is your last name?");
					String lastName = scan.next();
					System.out.println("What is your password?");
					String password = scan.next();
				
				if (firstName.equals("Marilyn") && lastName.equals("Monroe")&& password.equals("Skirts")) {
					AdminLogin.adminLogin();
				}
				else
					break;
				case 2:
					System.out.println("What is your first name?");
					String firstName1 = scan.next();
					System.out.println("What is your last name?");
					String lastName1 = scan.next();
					System.out.println("What is your password?");
					String password1 = scan.next();
					User tempUser = new User(firstName1, lastName1, password1);
					
					UserService.login(tempUser);
					UserLogin.userLogin();
					
					break;
				}
			}
			
			if(mainOption == 2) {
				SignUp.signUp();
			}
		} catch (IncorrectInputException e) {
			LogSingleton.info("An error has occurred");
			startMenu();
		}
	}
}

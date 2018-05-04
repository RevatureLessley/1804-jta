package com.bank.factory;

import java.util.Scanner;

import com.bank.exception.UserNotFoundException;
import com.bank.mainmenu.MainMenu;
import com.bank.model.User;
import com.bank.service.JunctionService;
import com.bank.service.ProductService;
import com.bank.service.UserService;

public class UserLogin {
	
	static Scanner scan = new Scanner(System.in);
	public static void userLogin() throws UserNotFoundException {
		
		System.out.println(UserService.getUsers());
		System.out.println("Please re-enter the first name");
		String fname = scan.next();
		System.out.println("Please re-enter the last name");
		String lname = scan.next();
		User user = new User(fname,lname);
		
		System.out.println("1. Add to cart");
		System.out.println("2. Remove from cart");
		System.out.println("3. View Cart");
		System.out.println("4. Exit");
		
		try(Scanner scan = new Scanner(System.in)){
			int userOption = scan.nextInt();

			switch(userOption) {
			
			case 1:
				System.out.println("Please select a product to add to the cart");
				System.out.println(ProductService.getAllProducts());
				System.out.println("Product name?");
				String productName = scan.next();
				JunctionService.addToCart(user, ProductService.getProduct(productName));
				
				break;
			case 2:
				
				break;
				
			case 3:
				System.out.println(JunctionService.getCart(user.getFirst_name(), user.getLast_name())); 
				
				break;
			
			case 4:
				System.exit(0);
			}
			MainMenu.startMenu();
		}

	}

}
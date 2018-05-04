package com.revature.application;

import com.revature.factory.Reimbursement;
import com.revature.reimbursement.FoodReimbursement;
import com.revature.reimbursement.TravelReimbursement;
import com.revature.service.ReimbursementService;

public class Application {

	public static void main(String[] args) {
//		Manager m = new Manager();
//		m.setFirstname("Jesse");
//		m.setManager_id(4);
//		m.setId(14);
//		System.out.println(m.toString());
//		
//		Employee employee = new Employee();
//		employee.setUsername("Skip_a_few");
//		employee.setPassword("penguins");
//		EmployeeService.insertUser(employee);
//		employee.setUsername("Allie");
//		employee.setPassword("penguins");
//		EmployeeService.insertUser(employee);
//		employee.setUsername("Kinsey");
//		employee.setPassword("penguins");
//		EmployeeService.insertUser(employee);
		
//		Employee employee = new Employee();
//		employee = EmployeeService.getEmployee(2);
//		System.out.println(employee);
		
//		List<Employee> employees = new ArrayList<>();
//		for (Employee employee : EmployeeService.getAllEmployees()) {
//			System.out.println(employee);
//		}
		
//		Reimbursement frb = new FoodReimbursement();
//		frb.setAmount(54);
//		frb.setRequestor_id(3);
//		ReimbursementService.submitRequest(frb);
//		
//		Reimbursement trb = new TravelReimbursement();
//		trb.setAmount(3.98f);
//		trb.setRequestor_id(5);
//		ReimbursementService.submitRequest(trb);
//		
//		for (Reimbursement rb : ReimbursementService.getAllRequests()) {
//			System.out.println(rb);
//		}
		
//		Manager m = new Manager();
//		m.setUsername("Teemo");
//		m.setPassword("penguins");
//		ManagerService.insertManager(m);
		
//		System.out.println(ManagerService.getManager(1));
		
		ReimbursementService.approveReimbursement(3, 1, 22);
		ReimbursementService.denyReimbursement(5, 2, 23);
		
		for (Reimbursement rb : ReimbursementService.getAllRequests()) {
			System.out.println(rb + "\n");
		}
	}
}

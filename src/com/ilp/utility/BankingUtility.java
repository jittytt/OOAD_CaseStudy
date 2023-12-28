package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.CreateCustomerAccount;
import com.ilp.service.CreateProduct;
import com.ilp.service.CreateService;
import com.ilp.service.CustomerAccountService;

public class BankingUtility {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Service> services = new ArrayList<Service>();
		ArrayList<Product> products = new ArrayList<Product>();
		Customer customer = null;
		Account account = null;
		
		char loopChoice = 'y';
		do {
			System.out.println("*********Welcome To Bank************ ");
			System.out.println("1. Create Service");
			System.out.println("2. Create Product");
			System.out.println("3. Create Customer");
			System.out.println("4. Manage Accounts");
			System.out.println("5. Display Customer");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");
			int mainMenuChoice = scanner.nextInt();
			switch(mainMenuChoice) {
			case 1: services.addAll(CreateService.serviceListCreation());
					//System.out.println("Size is "+services.size());
					break;
			case 2: products.addAll(CreateProduct.productCreation(services));	
					//System.out.println(products);
					break;
			case 3: if(customer == null) {
						account = CreateCustomerAccount.accountCreation(products);
						customer = CreateCustomerAccount.customerCreation(account);
					}
					else {
						account = CreateCustomerAccount.accountCreation(products);
						ArrayList<Account> tempAccountList = customer.getAccountList();
						tempAccountList.add(account);
						customer.setAccountList(tempAccountList);
					}
					System.out.println(customer);
					break;
			case 4: if(customer == null)
						System.out.println("Customer is not created");
					else
						CustomerAccountService.manageAccounts(customer);
					break;
			case 5: if(customer == null)
						System.out.println("Customer is not created");
					else
						CustomerAccountService.displayCustomerAccount(customer);
					break;
			case 6: return;
			default: System.out.println("Invalid Choice!!!");
			}
			System.out.print("Do you want to continue? ");
			loopChoice = scanner.next().charAt(0);
		}while(loopChoice == 'y');
			
	}
}	


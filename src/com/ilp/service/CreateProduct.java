package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CreateProduct {

	public static ArrayList<Product> productCreation(ArrayList<Service> services) {
		Scanner scanner =  new Scanner(System.in);
		

		ArrayList<Product> productList = new ArrayList<Product>();
		
		char serviceChoice = 'y';
		char productChoice = 'y';
		
		System.out.println("********Choose from below********");
		System.out.println("P001. SavingsMaxAccount\nP002. CurrentAccount\nP003. LoanAccount");
		
		do {
			ArrayList<Service> serviceList = new ArrayList<Service>();
			System.out.print("Enter the product code: ");
			String pCode = scanner.next();
			
			System.out.print("Enter the product name: ");
			String pName = scanner.next();
				
				for(Service service : services)
				{
					System.out.print("\nDo you want to add "+service.getServiceName()+" to this account?");
					serviceChoice = scanner.next().charAt(0);
					if(serviceChoice == 'y')
					{
						serviceList.add(service);
						System.out.println("\n"+service.getServiceName()+" added to your "+pName+".");
					}
				}
				
				switch(pCode) {
				case "P001" : int minBalance = 1000;
					productList.add(new SavingsMaxAccount(pCode, pName, serviceList, minBalance));
					break;
				case "P002" : 
					productList.add(new CurrentAccount(pCode, pName, serviceList));
					break;
				case "P003" : 
					productList.add(new LoanAccount(pCode, pName, serviceList, 0.3));
					break;
				default: System.out.println("Invalid product code!!");
				}
				
			System.out.print("\nDo you want to add more products? ");
			productChoice = scanner.next().charAt(0);
		}while(productChoice == 'y');
		
		return productList;
	}

	}

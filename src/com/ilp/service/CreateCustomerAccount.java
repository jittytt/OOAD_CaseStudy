package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;

public class CreateCustomerAccount {

	public static Account accountCreation(ArrayList<Product> productList) {
		
		Scanner scanner = new Scanner(System.in);
		int accountChoice, i=1;
		System.out.println("***********Accounts Available***********\r\n");
		for(Product product : productList) {
			System.out.println(i+". "+product.getProductName());
			i++;
		}
				
		System.out.print("Enter your choice: ");
		accountChoice = scanner.nextInt();
		
		System.out.print("Enter the account number: ");
		String accountNo = scanner.next();
		
		String accountName = productList.get(accountChoice-1).getProductName();
		
		System.out.print("Enter the account balance: ");
		double balanceAmount = scanner.nextDouble();
		
		if(productList.get(accountChoice-1) instanceof SavingsMaxAccount)
		{
			SavingsMaxAccount accountProduct = (SavingsMaxAccount)productList.get(accountChoice-1);
			while(balanceAmount < accountProduct.getMinimumBalance())
			{
				System.out.println("Balance not sufficient!!Needs "+accountProduct.getMinimumBalance()+" as minimum balance.");
				System.out.print("Enter the account balance: ");
				balanceAmount = scanner.nextDouble();
			}
		}
		return (new Account(accountNo, accountName, balanceAmount, productList.get(accountChoice-1)));
	}

	public static Customer customerCreation(Account account) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the customer code: ");
		String customerCode = scanner.next();
		
		System.out.print("Enter the customer name: ");
		String customerName = scanner.next();
		
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account);
		
		return (new Customer(customerCode, customerName, accountList));
	}
	
	
	
}

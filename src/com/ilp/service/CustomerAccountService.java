package com.ilp.service;


import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerAccountService {

	public static void manageAccounts(Customer customer) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Account> tempAccountList = null;
		String accountChoice = null;
		int accountServiceChoice;
		Account tempAccount = null;
		char manageAccountChoice = 'y';
		
		System.out.println("Enter customer code: ");
		String customerId = scanner.next();
		if(customerId.equalsIgnoreCase(customer.getCustomerCode()))
		{
			System.out.println(customer.getCustomerName()+" has the following accounts");
			tempAccountList = customer.getAccountList();
			for(Account account : tempAccountList)
			{
				System.out.println(account.getAccountNo()+". "+account.getAccountType());
			}
			System.out.println("Enter the account number : ");
			accountChoice = scanner.next();
			
			for(Account account : tempAccountList)
			{
				if(account.getAccountNo().equalsIgnoreCase(accountChoice))
				{
					tempAccount = account;
					break;
				}
			}
			do {
				System.out.println("1. Deposit\t2. Withdraw\t3. Display Balance");
				System.out.println("Enter your choice: ");
				accountServiceChoice = scanner.nextInt();
				
				switch(accountServiceChoice)
				{
				case 1: depositInAccount(tempAccount);
						break;
				case 2: withdrawFromAccount(tempAccount);
						break;
				case 3: System.out.println(customer.getCustomerName()+", your balance in each account are:");
						displayCustomerAccount(tempAccountList);
						break;
				default: System.out.println("Invalid Choice!!");
				}
				
				System.out.println("\nDo you want to continue accessing accounts? ");
				manageAccountChoice = scanner.next().charAt(0);
			}while(manageAccountChoice == 'y');
		}
		else {
			System.out.println("You entered wrong customer code!!!");
		}
	}

	private static void displayCustomerAccount(ArrayList<Account> accountList) {
		
		System.out.println("\nAccountType		  Balance");
		System.out.println("*******************************");
		for(Account account: accountList)
		{
			System.out.println(account.getAccountType()+"		"+account.getBalance());
		}

	}

	private static void depositInAccount(Account account) {
		
		Scanner scanner = new Scanner(System.in);
		
		int depositChoice;
		
		System.out.println("Enter the amount to be deposited: ");
		double deposit = scanner.nextDouble();
		
		if(account.getProduct() instanceof LoanAccount)
		{
			System.out.println("Is the deposit done through :\n1.Cash Deposit\n2.Cheque Deposit");
			System.out.print("Enter your choice");
			depositChoice = scanner.nextInt();
			
			if(depositChoice == 2) {
				deposit = (deposit-(deposit*0.003));
			}
	
		}
		account.setBalance(account.getBalance()+deposit);
		System.out.println("\nYour current balance is: "+account.getBalance());

	}

	private static void withdrawFromAccount(Account account) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter the amount to be withdrawn: ");
		double withdraw = scanner.nextDouble();
		
		Product accountProduct = account.getProduct();
		
		double balanceAmount = account.getBalance()-withdraw;
		
		if(accountProduct instanceof SavingsMaxAccount) 
		{
			SavingsMaxAccount savingsAccount = (SavingsMaxAccount)accountProduct;
			if(balanceAmount < savingsAccount.getMinimumBalance())
			{
				System.out.println("\nSorry!!!!!!!!!!!!! A mininmum balance of ₹"+savingsAccount.getMinimumBalance()+" should be maintained in the account.");

			}
			else {
				account.setBalance(balanceAmount);
			}
		}
		else if(accountProduct instanceof LoanAccount) {	
			System.out.println("\nSorry!!!!!!!!!!You can't withdraw money from "+accountProduct.getProductName()+" account");
		}
		
		else {
			if(withdraw > account.getBalance())
				System.out.println("\nSorry!!!!!!!!!!You don't have sufficient balance");
				
			else
				account.setBalance(balanceAmount);
		}
		System.out.println("\nYour current balance is: "+account.getBalance());
	}

	public static void displayCustomerAccount(Customer customer) {
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId	CustomerName		AccountType		  Balance");
		ArrayList<Account> accounts = customer.getAccountList();
		for(Account account : accounts) 
		{
			System.out.println(customer.getCustomerCode()+"	           "+customer.getCustomerName()+"		"+account.getAccountType()+"		"+account.getBalance());
			System.out.print("Services Provided");
			ArrayList<Service> services = account.getProduct().getServiceList();
			for(Service service: services)
			{
				System.out.println(service.getServiceName()+"  ");
			}
		}
	}
	
	
}

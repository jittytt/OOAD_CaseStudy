package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Service;

public class CreateService {

	public static ArrayList<Service> serviceListCreation() {
		
		Scanner scanner =  new Scanner(System.in);
		ArrayList<Service> services = new ArrayList<Service>();
		char serviceChoice = 'y';
		Service service = null;
		System.out.println("********Available Services********");
		System.out.println("SC001. CashDeposit\nSC002. ATM Withdrawal\nSC003. OnlineBanking"
				+ "\nSC004. MobileBanking\nSC005. ChequeDeposit");
		do {
			System.out.print("Enter the service code: ");
			String serviceCode = scanner.next();
			System.out.print("Enter the service name: ");
			String serviceName = scanner.next();
			System.out.print("Enter the service rate: ");
			double serviceRate = scanner.nextDouble();
			service = new Service(serviceCode, serviceName, serviceRate);
			services.add(service);
			System.out.print("Do you want to make more services?");
			serviceChoice = scanner.next().charAt(0);
		}while(serviceChoice == 'y');
		return services;
	}
}

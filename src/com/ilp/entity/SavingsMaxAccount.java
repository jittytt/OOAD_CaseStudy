package com.ilp.entity;

import java.util.ArrayList;
import java.util.Scanner;

public class SavingsMaxAccount extends Product {
	private double minimumBalance;

	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList,
			double minimumBalance) {
		super(productCode, productName, serviceList);
		this.minimumBalance = minimumBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	
}

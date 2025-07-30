package com.fdmgroup.AccountManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Checking_Joined")
public class CheckingAccount extends Account {
	
    private int nextCheckNumber;

	public CheckingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckingAccount(int nextCheckNumber) {
		super();
		this.nextCheckNumber = nextCheckNumber;
	}

	public int getNextCheckNumber() {
		return nextCheckNumber;
	}

	public void setNextCheckNumber(int nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}
    
    
}

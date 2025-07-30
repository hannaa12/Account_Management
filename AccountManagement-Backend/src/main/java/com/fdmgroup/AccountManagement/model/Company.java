package com.fdmgroup.AccountManagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "COMPANY")
public class Company extends Customer {
    private long businessNumber;

	public Company(long businessNumber) {
		super();
		this.businessNumber = businessNumber;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(long businessNumber) {
		this.businessNumber = businessNumber;
	}
    

}


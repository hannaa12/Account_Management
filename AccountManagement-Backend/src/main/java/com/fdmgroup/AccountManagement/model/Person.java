package com.fdmgroup.AccountManagement.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue(value = "PERSON")
public class Person extends Customer {
	
	
    private long sinNumber;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(long sinNumber) {
		super();
		this.sinNumber = sinNumber;
	}

	public long getSinNumber() {
		return sinNumber;
	}

	public void setSinNumber(long sinNumber) {
		this.sinNumber = sinNumber;
	}


    
}

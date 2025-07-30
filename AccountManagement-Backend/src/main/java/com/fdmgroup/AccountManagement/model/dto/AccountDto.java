package com.fdmgroup.AccountManagement.model.dto;

import jakarta.validation.constraints.NotNull;

public class AccountDto {

	private Long customerId;

	@NotNull(message = "Account type is required")
	private String accountType;
	
	@NotNull(message = "Balance is required")
	private Double balance;

	private Double interestRate;
	private Integer nextCheckNumber;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Integer getNextCheckNumber() {
		return nextCheckNumber;
	}

	public void setNextCheckNumber(Integer nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}
}

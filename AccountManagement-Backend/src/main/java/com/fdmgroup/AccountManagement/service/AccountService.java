package com.fdmgroup.AccountManagement.service;

import java.util.List;

import com.fdmgroup.AccountManagement.model.Account;
import com.fdmgroup.AccountManagement.model.dto.AccountDto;

public interface AccountService {

	Account createAccount(AccountDto dto);
	List<Account> getAccountsByCity(String city);
	List<Account> getAllAccounts();
}

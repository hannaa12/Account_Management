package com.fdmgroup.AccountManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.AccountManagement.model.Account;
import com.fdmgroup.AccountManagement.model.CheckingAccount;
import com.fdmgroup.AccountManagement.model.Customer;
import com.fdmgroup.AccountManagement.model.SavingsAccount;
import com.fdmgroup.AccountManagement.model.dto.AccountDto;
import com.fdmgroup.AccountManagement.repository.AccountRepository;
import com.fdmgroup.AccountManagement.repository.CheckingAccountRepository;
import com.fdmgroup.AccountManagement.repository.CustomerRepository;
import com.fdmgroup.AccountManagement.repository.SavingsAccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	    private CustomerRepository customerRepository;
	    private SavingsAccountRepository savingsAccountRepository;
	    private CheckingAccountRepository checkingAccountRepository;
	    private AccountRepository accountRepository;

	    
	@Autowired
	public AccountServiceImpl(CustomerRepository customerRepository,
				SavingsAccountRepository savingsAccountRepository,
				CheckingAccountRepository checkingAccountRepository, AccountRepository accountRepository) {
			super();
			this.customerRepository = customerRepository;
			this.savingsAccountRepository = savingsAccountRepository;
			this.checkingAccountRepository = checkingAccountRepository;
			this.accountRepository = accountRepository;
		}



	@Override
	public Account createAccount(AccountDto accountDto) {
        Customer customer = customerRepository.findById(accountDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Account savedAccount;

        switch (accountDto.getAccountType().toUpperCase()) {
            case "SAVINGS":
                if (accountDto.getInterestRate() == null) {
                    throw new IllegalArgumentException("Interest rate is required for Savings Account");
                }
                SavingsAccount savings = new SavingsAccount();
                savings.setBalance(accountDto.getBalance());
                savings.setInterestRate(accountDto.getInterestRate());
                savings.setCustomer(customer);
                savedAccount = savingsAccountRepository.save(savings);
                break;

            case "CHECKING":
                if (accountDto.getNextCheckNumber() == null) {
                    throw new IllegalArgumentException("Next check number is required for Checking Account");
                }
                CheckingAccount checking = new CheckingAccount();
                checking.setBalance(accountDto.getBalance());
                checking.setNextCheckNumber(accountDto.getNextCheckNumber());
                checking.setCustomer(customer);
                savedAccount = checkingAccountRepository.save(checking);
                break;

            default:
                throw new IllegalArgumentException("Invalid account type: must be CHECKING or SAVINGS");
        }

        return savedAccount;
    }
	
	 public List<Account> getAccountsByCity(String city) {
	        return accountRepository.findByCustomer_Address_CityIgnoreCase(city);
	    }


	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

}

package com.fdmgroup.AccountManagement.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.AccountManagement.model.Account;
import com.fdmgroup.AccountManagement.model.dto.AccountDto;
import com.fdmgroup.AccountManagement.service.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@PostMapping
	public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDto dto) {
		System.out.println("Received customerId: " + dto.getCustomerId());

		Account createdAccount = accountService.createAccount(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountId}")
				.buildAndExpand(createdAccount.getAccountId()).toUri();
		return ResponseEntity.created(location).body(createdAccount);
	}
	
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Account>> getAccountsByCity(@PathVariable String city) {
        List<Account> accounts = accountService.getAccountsByCity(city);
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
 
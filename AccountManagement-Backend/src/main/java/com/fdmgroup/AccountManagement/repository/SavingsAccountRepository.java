package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

}

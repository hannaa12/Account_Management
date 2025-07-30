package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.CheckingAccount;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long>{

}

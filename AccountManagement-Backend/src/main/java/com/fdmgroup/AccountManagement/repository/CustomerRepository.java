package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

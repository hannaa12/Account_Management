package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
}


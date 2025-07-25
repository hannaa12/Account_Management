package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	
}


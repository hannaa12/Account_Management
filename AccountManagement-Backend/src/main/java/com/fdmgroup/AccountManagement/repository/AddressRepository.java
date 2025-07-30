package com.fdmgroup.AccountManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.AccountManagement.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

} 

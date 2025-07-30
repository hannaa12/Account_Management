package com.fdmgroup.AccountManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.AccountManagement.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {


	@Query("SELECT a FROM Account a WHERE a.customer.address.city = :city")
	List<Account> findByCustomer_Address_CityIgnoreCase(@Param("city") String city);

}

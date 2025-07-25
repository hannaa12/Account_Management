package com.fdmgroup.AccountManagement.service;

import java.util.List;

import com.fdmgroup.AccountManagement.exception.GeocodingServiceException;
import com.fdmgroup.AccountManagement.model.Address;
import com.fdmgroup.AccountManagement.model.Customer;
import com.fdmgroup.AccountManagement.model.dto.CustomerDto;
import com.fdmgroup.AccountManagement.model.dto.CustomerUpdateDTO;

import jakarta.validation.Valid;

public interface CustomerService {

	Customer createCustomer(CustomerDto dto) throws GeocodingServiceException;
	Customer findByCustomerId(long customerId);
	void deleteCustomerById(Long customerId);
	List<Customer> getAllCustomers();
	Customer updateCustomer(Long customerId, CustomerUpdateDTO customerDto);


	Address getGeocoder(@Valid Address address) throws GeocodingServiceException;
	

}

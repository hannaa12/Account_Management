package com.fdmgroup.AccountManagement.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.AccountManagement.exception.GeocodingServiceException;
import com.fdmgroup.AccountManagement.model.Customer;
import com.fdmgroup.AccountManagement.model.dto.CustomerDto;
import com.fdmgroup.AccountManagement.model.dto.CustomerUpdateDTO;
import com.fdmgroup.AccountManagement.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@Operation(summary = "Creates a new Customers")
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDto customerDto) throws GeocodingServiceException{
		Customer createdCustomer = customerService.createCustomer(customerDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
				.buildAndExpand(createdCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(createdCustomer);
	}

	@Operation(summary = "Gets Customers by specified ID")
	@ApiResponses(value = {@ApiResponse(responseCode = "200",
	content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
			@Content(mediaType = MediaType.APPLICATION_XML_VALUE)},
	description = "Get Customer by id"),
	@ApiResponse(responseCode = "404", description = "Customer not found")
	})
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> findByCustomerId(@PathVariable Long customerId) {
		return ResponseEntity.ok(customerService.findByCustomerId(customerId));
	}
	
	
	@Operation(summary = "Updates a Customer")
	@PutMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody CustomerUpdateDTO customerDto) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, customerDto));
    }

	@Operation(summary = "Deletes a Customers by specified ID")
	@DeleteMapping("/{customerId}")
	public ResponseEntity<String> deleteCustomerById(@PathVariable Long customerId) {
		customerService.deleteCustomerById(customerId);
		return ResponseEntity.ok("Customer with ID " + customerId + " has been deleted successfully.");
	}

	@Operation(summary = "Gets all Customers")
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
}

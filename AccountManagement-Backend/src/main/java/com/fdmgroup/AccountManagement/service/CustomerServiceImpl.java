package com.fdmgroup.AccountManagement.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fdmgroup.AccountManagement.exception.GeocodingServiceException;
import com.fdmgroup.AccountManagement.exception.InvalidPostalCodeException;
import com.fdmgroup.AccountManagement.model.Address;
import com.fdmgroup.AccountManagement.model.Company;
import com.fdmgroup.AccountManagement.model.Customer;
import com.fdmgroup.AccountManagement.model.GeoCoderResponse;
import com.fdmgroup.AccountManagement.model.Person;
import com.fdmgroup.AccountManagement.model.dto.CustomerDto;
import com.fdmgroup.AccountManagement.model.dto.CustomerUpdateDTO;
import com.fdmgroup.AccountManagement.repository.AddressRepository;
import com.fdmgroup.AccountManagement.repository.CustomerRepository;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

	

	public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
		super();
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
	}

	@Override
	public Customer createCustomer(@Valid CustomerDto customerDto) throws GeocodingServiceException {
		
		Address address;
		if(customerDto.getCity()== null || customerDto.getProvince()==null) {
		address = new Address(customerDto.getStreetNumber(), customerDto.getPostalCode());
		address = getGeocoder(address);
		}else {
			address = new Address(customerDto.getStreetNumber(),customerDto.getPostalCode(),customerDto.getCity(),customerDto.getProvince());
		}
		
		addressRepository.save(address);
		
		Customer saved;

        if ("PERSON".equalsIgnoreCase(customerDto.getType())) {
            if (customerDto.getSinNumber() == null) {
                throw new IllegalArgumentException("sinNumber is required for PERSON type");
            }
            Person person = new Person();
            person.setName(customerDto.getName());
            person.setSinNumber(customerDto.getSinNumber());
            person.setAddress(address);
            saved = person;

        } else if ("COMPANY".equalsIgnoreCase(customerDto.getType())) {
            if (customerDto.getBusinessNumber() == null) {
                throw new IllegalArgumentException("businessNumber is required for COMPANY type");
            }
            Company company = new Company();
            company.setName(customerDto.getName());
            company.setBusinessNumber(customerDto.getBusinessNumber());
            company.setAddress(address);
            saved = company;

        } else {
            throw new IllegalArgumentException("Invalid customer type: must be PERSON or COMPANY");
        }

		return customerRepository.save(saved);
	}

	@Override
	public Customer findByCustomerId(long customerId) {
	    return customerRepository.findById(customerId)
	            .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found"));
	}


	@Override
	public void deleteCustomerById(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public Customer updateCustomer(Long customerId, CustomerUpdateDTO customerDto) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow();

        if (customerDto.getName() != null) customer.setName(customerDto.getName());
        if (customerDto.getPostalCode() != null) customer.getAddress().setPostalCode(customerDto.getPostalCode());
        if (customerDto.getCity() != null) customer.getAddress().setCity(customerDto.getCity());
        if (customerDto.getProvince() != null) customer.getAddress().setProvince(customerDto.getProvince());

        return customerRepository.save(customer);
	}
	

	@Override
	public Address getGeocoder(@Valid Address address) throws GeocodingServiceException {
		WebClient webClient = WebClient.builder().baseUrl("https://geocoder.ca").build();

		String postalCode = address.getPostalCode();

		try {
			GeoCoderResponse geocoderResponse = webClient.get()
					.uri(uriBuilder -> uriBuilder.queryParam("postal", postalCode).queryParam("json", "1").build())
					.retrieve()
					.onStatus(status -> status.value() == HttpStatus.BAD_REQUEST.value(),
							response -> Mono.error(new RuntimeException("Invalid postal code: " + postalCode)))
					.bodyToMono(GeoCoderResponse.class).block();

			if (geocoderResponse != null && geocoderResponse.getStandard() != null) {
				String city = geocoderResponse.getStandard().getCity();
				String province = geocoderResponse.getStandard().getProv();

				if (city == null || city.isBlank() || province == null || province.isBlank()) {
					throw new InvalidPostalCodeException("Invalid or unrecognized postal code: " + postalCode);
				}

				address.setCity(city);
				address.setProvince(province);
			} else {
				throw new InvalidPostalCodeException("Could not parse location data for postal code: " + postalCode);
			}

			return address;

		} catch (WebClientResponseException | InvalidPostalCodeException e) {
			throw new GeocodingServiceException(e.getMessage());
		}
	}

}

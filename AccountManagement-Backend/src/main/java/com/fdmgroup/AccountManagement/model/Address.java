package com.fdmgroup.AccountManagement.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_SEQ_GEN")
	@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_SEQ")
    private long addressId;
    
    @NotNull
    private String streetNumber;
    
    @NotNull
    private String postalCode;
    private String city;
    private String province;
    
    
    public Address() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Address(String streetNumber, String postalCode, String city, String province) {
		super();
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}


	public Address(@NotNull String streetNumber, @NotNull String postalCode) {
		super();
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
	}


	public long getAddressId() {
		return addressId;
	}


	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", streetNumber=" + streetNumber + ", postalCode=" + postalCode
				+ ", city=" + city + ", province=" + province + "]";
	}
	

}

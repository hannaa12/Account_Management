package com.fdmgroup.AccountManagement.model.dto;

public class CustomerDto {

	private String name;
	private String streetNumber;
	private String postalCode;
	private String type; // "PERSON" or "COMPANY"
    private Long sinNumber;     
    private Long businessNumber; 
    private String city;
    private String province;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSinNumber() {
		return sinNumber;
	}

	public void setSinNumber(Long sinNumber) {
		this.sinNumber = sinNumber;
	}

	public Long getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(Long businessNumber) {
		this.businessNumber = businessNumber;
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
	
	

}

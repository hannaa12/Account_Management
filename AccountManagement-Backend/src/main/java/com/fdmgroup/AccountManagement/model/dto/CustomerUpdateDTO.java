package com.fdmgroup.AccountManagement.model.dto;

public class CustomerUpdateDTO {

    private String name;
    private String postalCode;
    private String city;
    private String province;

    // Constructors
    public CustomerUpdateDTO() {}



    public CustomerUpdateDTO(String name, String postalCode, String city, String province) {
		super();
		this.name = name;
		this.postalCode = postalCode;
		this.city = city;
		this.province = province;
	}





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

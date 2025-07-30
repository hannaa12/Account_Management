package com.fdmgroup.AccountManagement.model;

public class GeoCoderResponse {

	private Standard standard;

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

	@Override
	public String toString() {
		return "GeocoderResponse [standard=" + standard + "]";
	}
    
    
    
}

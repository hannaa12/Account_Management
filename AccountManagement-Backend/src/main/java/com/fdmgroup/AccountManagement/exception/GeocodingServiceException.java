package com.fdmgroup.AccountManagement.exception;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public class GeocodingServiceException extends Exception {

    public GeocodingServiceException(String message) {
        super(message);
    }
    
}

package com.iptracker.exceptions;

public class ServiceUnavailableException extends RuntimeException {
	
	private static final long serialVersionUID = 3968414789127681805L;
	
	public final static String SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable.";
	
	public ServiceUnavailableException(String serviceURL) {
		super(SERVICE_UNAVAILABLE_MESSAGE + " URL: " + serviceURL);
	}
	
	public ServiceUnavailableException(String serviceURL, String serviceHTTPCode) {
		super(SERVICE_UNAVAILABLE_MESSAGE + " URL: " + serviceURL + " HTTP code: " + serviceHTTPCode);
	}
}
package com.iptracker.exceptions;

public class InvalidIPException extends RuntimeException {
	
	private static final long serialVersionUID = 2968414789127681805L;
	
	public final static String INVALID_IP_ADDRESS_MESSAGE = "Invalid public IPv4 address.";
	
	public InvalidIPException(String ip) {
		super(INVALID_IP_ADDRESS_MESSAGE + " IP: " + ip);
	}
}
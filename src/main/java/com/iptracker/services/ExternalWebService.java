package com.iptracker.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iptracker.exceptions.ServiceUnavailableException;
import com.iptracker.server.Configuration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ExternalWebService {
	
	private final static int RESPONSE_STATUS_OK = 200;
	
	private Client client;
	private WebResource webResource;
	private String url;
	private int maxRetryAttempts;
	
	private final Logger log;
	
	public ExternalWebService(String serviceURLProperty) {
		url = Configuration.getInstance().getStringPropertyValue(serviceURLProperty);
		client = Client.create();
		webResource = client.resource(url);
		log = LoggerFactory.getLogger(this.getClass());
		maxRetryAttempts = Configuration.getInstance().getIntegerPropertyValue("service.retry.max.attempts");
	}
	
	public String invokeService() {
		try {
			int attempts = 0;
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			while(response.getStatus() != RESPONSE_STATUS_OK && attempts < maxRetryAttempts) {
				response = webResource.accept("application/json").get(ClientResponse.class);
				attempts++;
			}
			if(response.getStatus() != RESPONSE_STATUS_OK) {
				throw new ServiceUnavailableException(url, String.valueOf(response.getStatus()));
			}
			String serviceResponse = response.getEntity(String.class);
			log.info("GET: " + webResource.getURI() + "\nResponse: " + serviceResponse);
			return serviceResponse;
		} 
		catch (Exception exception) {
			throw new ServiceUnavailableException(url);
		}
	}
	
	public String invokeService(String extraParameters) {
		if(extraParameters != null) {
			webResource = client.resource(url + extraParameters);
		}
		return invokeService();
	}
	
	public boolean isServiceAvailable() {
		try {
			ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
			return response.getStatus() == RESPONSE_STATUS_OK;		
		}
		catch(Exception exception) {
			return false;
		}
	}
	
	public boolean isServiceAvailable(String extraParameters) {
		if(extraParameters != null) {
			webResource = client.resource(url + extraParameters);
		}
		return isServiceAvailable();
	}
}
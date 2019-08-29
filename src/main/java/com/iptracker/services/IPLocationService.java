package com.iptracker.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iptracker.exceptions.InvalidIPException;

public class IPLocationService extends ExternalWebService {
	
	private final static String IP_LOCATION_SERVICE_URL_CONFIG_VALUE = "iplocation.service.url";

	public IPLocationService() {
		super(IP_LOCATION_SERVICE_URL_CONFIG_VALUE);
	}
	
	public String getCountryCodeFromIP(String ipAddress) throws InvalidIPException {
		String jsonResponse = invokeService(ipAddress);
		JsonParser jsonParser = new JsonParser();
		JsonObject response = jsonParser.parse(jsonResponse).getAsJsonObject();
		String countryCode = response.get("countryCode").getAsString();
		if(countryCode.isEmpty()) {
			throw new InvalidIPException(ipAddress);
		}
		return countryCode;
	}
}

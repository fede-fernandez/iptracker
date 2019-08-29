package com.iptracker.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.iptracker.entities.Currency;

public class CurrencyService extends ExternalWebService {
	
	private final static String CURRENCY_SERVICE_URL_CONFIG_VALUE = "currency.service.url";

	public CurrencyService() {
		super(CURRENCY_SERVICE_URL_CONFIG_VALUE);
	}

	public List<Currency> getCurrencies() {
		List<Currency> currencies = new ArrayList<Currency>();
		String jsonResponse =  invokeService();
		JsonParser jsonParser = new JsonParser();
		Set<Entry<String, JsonElement>> ratesAsJson = 
				jsonParser.parse(jsonResponse).getAsJsonObject().get("rates").getAsJsonObject().entrySet();
		ratesAsJson.forEach(rateAsJson -> 
				currencies.add(new Currency(rateAsJson.getKey(), rateAsJson.getValue().getAsDouble())));
		return currencies;
	}
}
package com.iptracker.services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iptracker.entities.Coordinates;
import com.iptracker.entities.Country;
import com.iptracker.entities.Currency;
import com.iptracker.entities.Language;

public class CountryService extends ExternalWebService {

	private final static String COUNTRY_SERVICE_URL_CONFIG_VALUE = "country.service.url";

	public CountryService() {
		super(COUNTRY_SERVICE_URL_CONFIG_VALUE);
	}

	public List<Country> getCountries() {
		List<Country> countries = new ArrayList<Country>();
		String jsonResponse = invokeService();
		
		JsonParser jsonParser = new JsonParser();
		JsonArray countriesAsJson = jsonParser.parse(jsonResponse).getAsJsonArray();
		countriesAsJson.forEach(countryAsJson -> 
				countries.add(parseJsonCountry(countryAsJson.getAsJsonObject())));
		
		return countries;
	}
	
	public Country parseJsonCountry(JsonObject countryAsJson) {
		Country country = new Country();
		String countryName = countryAsJson.get("name").getAsString();
		country.setName(countryName);
		
		String countryISOCode = countryAsJson.get("alpha2Code").getAsString();
		country.setIsoCode(countryISOCode);
		
		JsonArray languagesAsJson = countryAsJson.get("languages").getAsJsonArray();
		languagesAsJson.forEach(languageAsJson -> 
			addParsedLanguageToCountry(languageAsJson.getAsJsonObject(), country));
		
		JsonArray currenciesAsJson = countryAsJson.get("currencies").getAsJsonArray();
		currenciesAsJson.forEach(currencyAsJson -> 
			addParsedCurrencyToCountry(currencyAsJson.getAsJsonObject(), country));
		
		JsonArray timezonesAsJson = countryAsJson.get("timezones").getAsJsonArray();
		timezonesAsJson.forEach(timezoneAsJson -> country.getTimezones().add(timezoneAsJson.getAsString()));
		
		JsonArray coordinatesAsJson = countryAsJson.get("latlng").getAsJsonArray();
		if(coordinatesAsJson.size() > 0) {
			double latitude = coordinatesAsJson.get(0).getAsDouble();
			double longitude = coordinatesAsJson.get(1).getAsDouble();
			country.setCoordinates(new Coordinates(latitude, longitude));
		}
		
		return country;
	} 
	
	public void addParsedLanguageToCountry(JsonObject languageAsJson, Country country) {
		if(languageAsJson.has("iso639_1") && languageAsJson.has("name")) {
			String languageISOCode = languageAsJson.get("iso639_1").getAsString();
			String languageName = languageAsJson.get("name").getAsString();
			country.addLanguage(new Language(languageISOCode, languageName));
		}
	}
	
	public void addParsedCurrencyToCountry(JsonObject currencyAsJson, Country country) {
		if(currencyAsJson.get("code") != null) {
			String currencyCode = currencyAsJson.get("code").getAsString();
			country.addCurrency(new Currency(currencyCode));
		}
	}
}
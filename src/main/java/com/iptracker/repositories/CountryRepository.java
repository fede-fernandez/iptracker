package com.iptracker.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iptracker.entities.Country;

public class CountryRepository {
	
	private static CountryRepository instance;

	private List<Country> countries;
	
	private CountryRepository () {
		countries = new ArrayList<Country>();
	}
	
	public static CountryRepository getInstance() {
		if(instance == null) {
			instance = new CountryRepository();
		}
		return instance;
	}
	
	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
	
	public Country getCountryByISOCode(String isoCode) {
		Country foundCountry = countries.stream().filter(
				country -> country.getIsoCode().equals(isoCode)).collect(Collectors.toList()).get(0);
		foundCountry.increaseTimesConsultedCount();
		return foundCountry;
	}
	
	public void resetCountriesConsultedCount() {
		countries.forEach(country -> country.setTimesConsultedCount(0));
	}
}
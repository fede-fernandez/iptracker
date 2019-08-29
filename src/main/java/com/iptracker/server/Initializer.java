package com.iptracker.server;

import com.iptracker.repositories.CountryRepository;
import com.iptracker.scheduler.CurrencyScheduler;
import com.iptracker.services.CountryService;

public class Initializer {
	
	public void initialize() {
		CountryService countryService = new CountryService();
		CountryRepository.getInstance().setCountries(countryService.getCountries());
		new CurrencyScheduler().initiateCurrencyScheduler();
	}
}
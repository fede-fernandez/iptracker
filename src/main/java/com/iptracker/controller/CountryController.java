package com.iptracker.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iptracker.entities.Country;
import com.iptracker.entities.CountryReport;
import com.iptracker.exceptions.InvalidIPException;
import com.iptracker.exceptions.ServiceUnavailableException;
import com.iptracker.repositories.CountryRepository;
import com.iptracker.repositories.CurrencyRepository;
import com.iptracker.services.IPLocationService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CountryController {
	
	public final static String UNKNOWN_ERROR_MESSAGE = "Unknown error.";
	public final static String NO_EXECUTIONS_MADE_YET_MESSAGE = "No executions made yet.";
	
	private final static String IP_REGEX = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
			+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ModelAndView traceIP(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		
		try {
			String ipAddress = request.queryParams("ip").trim();
			
			if(!isValidIP(ipAddress)) {
				throw new InvalidIPException(ipAddress);
			}
			
			String countryISOCode = new IPLocationService().getCountryCodeFromIP(ipAddress);
			
			Country country = CountryRepository.getInstance().getCountryByISOCode(countryISOCode);
			
			country.getCurrencies().forEach(currency -> 
				currency.setRate(CurrencyRepository.getInstance().getCurrencyRateFromCurrencyCode(currency.getCode())));
			
			model.put("servertime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			model.put("ip", ipAddress);
			model.put("country", country);
			return new ModelAndView(model, "country.ftl");
		}
		catch(InvalidIPException invalidIPException) {
			log.info(invalidIPException.getMessage());
			model.put("errorMessage", InvalidIPException.INVALID_IP_ADDRESS_MESSAGE);
			return new ModelAndView(model, "error.ftl");
		}
		catch(ServiceUnavailableException serviceUnavailableException) {
			log.info(serviceUnavailableException.getMessage());
			model.put("errorMessage", ServiceUnavailableException.SERVICE_UNAVAILABLE_MESSAGE);
			return new ModelAndView(model, "error.ftl");
		}
		catch(Exception exception) {
			log.error(UNKNOWN_ERROR_MESSAGE, exception);
			model.put("errorMessage", UNKNOWN_ERROR_MESSAGE);
			return new ModelAndView(model, "error.ftl");
		}
	}
	
	public ModelAndView report(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		
		try {
			CountryReport countryReport = new CountryReport();
			
			if(countryReport.getConsultedCountriesWithCoordinates().isEmpty()) {
				model.put("errorMessage", NO_EXECUTIONS_MADE_YET_MESSAGE);
				return new ModelAndView(model, "error.ftl");
			}

			model.put("furthest", countryReport.getFurthestCountryToArgentina());
			model.put("closest", countryReport.getClosestCountryToArgentina());
			model.put("consultedCountries", countryReport.getConsultedCountries());
			model.put("averageDistance", countryReport.getAverageDistance());

			return new ModelAndView(model, "report.ftl");
		}
		catch(Exception exception) {
			log.error(UNKNOWN_ERROR_MESSAGE, exception);
			model.put("errorMessage", UNKNOWN_ERROR_MESSAGE);
			return new ModelAndView(model, "error.ftl");
		}
	}
	
	public boolean isValidIP(String ip) {
		return ip.matches(IP_REGEX);
	}
}
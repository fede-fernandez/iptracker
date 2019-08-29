package com.iptracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iptracker.entities.Country;
import com.iptracker.repositories.CountryRepository;
import com.iptracker.repositories.CurrencyRepository;
import com.iptracker.server.Configuration;
import com.iptracker.services.CountryService;
import com.iptracker.services.CurrencyService;
import com.iptracker.services.IPLocationService;

public class TraceIPTest {
	
	private static IPLocationService ipLocationService;
	private static CountryService countryService;
	private static CurrencyService currencyService;

	@BeforeClass
	public static void initialize() {
		ipLocationService = new IPLocationService();
		countryService = new CountryService();
		currencyService = new CurrencyService();
		CountryRepository.getInstance().setCountries(countryService.getCountries());
		CurrencyRepository.getInstance().setCurrencies(currencyService.getCurrencies());
		CurrencyRepository.getInstance().modifyBaseCurrencyTo(
				Configuration.getInstance().getStringPropertyValue("currency.service.base.currency"));
	}

	@Test
	public void spainISOCodeTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(
				ipLocationService.getCountryCodeFromIP(TestData.SPANISH_IP));
		
		assertEquals(TestData.SPAIN_ISO_CODE, spain.getIsoCode());
	}
	
	@Test
	public void spainNameTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		
		assertEquals(TestData.SPAIN_COUNTRY_NAME, spain.getName());
	}
	
	@Test
	public void spainLanguageNameTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		
		assertTrue(spain.getLanguages().stream().anyMatch(
				language -> language.getName().equalsIgnoreCase(TestData.SPANISH_LANGUAGE_NAME)));
	}
	
	@Test
	public void spainLanguageISOCodeTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		
		assertTrue(spain.getLanguages().stream().anyMatch(
				language -> language.getIsoCode().equalsIgnoreCase(TestData.SPANISH_LANGUAGE_ISO_CODE)));
	}
	
	@Test
	public void spainCurrencyCodeTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		
		assertTrue(spain.getCurrencies().stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.EURO_CURRENCY_CODE)));
	}
	
	@Test
	public void spainTimezoneTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		
		assertTrue(spain.getTimezones().stream().anyMatch(
				timezone -> timezone.equalsIgnoreCase(TestData.UTC_TIME_ZONE_CODE)) 
				&& spain.getTimezones().stream().anyMatch(
						timezone -> timezone.equalsIgnoreCase(TestData.UTC_PLUS_ONE_TIME_ZONE_CODE)));
	}
	
	@Test
	public void unitedStatesISOCodeTest() {
		Country unitedStates = CountryRepository.getInstance().getCountryByISOCode(
				ipLocationService.getCountryCodeFromIP(TestData.AMERICAN_IP));
		
		assertEquals(TestData.UNITED_STATES_ISO_CODE, unitedStates.getIsoCode());
	}
	
	@Test
	public void unitedStatesNameTest() {
		Country unitedStates = CountryRepository.getInstance().getCountryByISOCode(TestData.UNITED_STATES_ISO_CODE);
		
		assertEquals(TestData.UNITED_STATES_COUNTRY_NAME, unitedStates.getName());
	}
	
	@Test
	public void distanceFromArgentinaToUruguayTest() {
		Country uruguay = CountryRepository.getInstance().getCountryByISOCode(TestData.URUGUAY_ISO_CODE);
		
		assertEquals(TestData.DISTANCE_FROM_ARGENTINA_TO_URUGUAY, 
				uruguay.getCoordinates().getDistanceToArgentina(), 1);
	}
	
	@Test
	public void distanceFromArgentinaToBrazilTest() {
		Country brazil = CountryRepository.getInstance().getCountryByISOCode(TestData.BRAZIL_ISO_CODE);

		assertEquals(TestData.DISTANCE_FROM_ARGENTINA_TO_BRAZIL, 
				brazil.getCoordinates().getDistanceToArgentina(), 1);
	}

	@Test
	public void distanceFromArgentinaToSpainTest() {
		Country spain = CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);

		assertEquals(TestData.DISTANCE_FROM_ARGENTINA_TO_SPAIN, 
				spain.getCoordinates().getDistanceToArgentina(), 1);
	}
	
	@Test
	public void distanceFromArgentinaToUnitedStatesTest() {
		Country unitedStates = CountryRepository.getInstance().getCountryByISOCode(TestData.UNITED_STATES_ISO_CODE);

		assertEquals(TestData.DISTANCE_FROM_ARGENTINA_TO_UNITED_STATES, 
				unitedStates.getCoordinates().getDistanceToArgentina(), 1);	
	}
	
	@Test
	public void distanceFromArgentinaToArgentinaTest() {
		Country argentina = CountryRepository.getInstance().getCountryByISOCode(TestData.ARGENTINA_ISO_CODE);

		assertEquals(TestData.DISTANCE_FROM_ARGENTINA_TO_ARGENTINA, 
				argentina.getCoordinates().getDistanceToArgentina(), 1);
	}
	
	@After
	public void reset() {
		CountryRepository.getInstance().resetCountriesConsultedCount();
	}
}
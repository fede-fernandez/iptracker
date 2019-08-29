package com.iptracker;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iptracker.entities.Country;
import com.iptracker.services.CountryService;

public class CountryServiceTest {
	
	private static List<Country> countries;
	
	@BeforeClass
	public static void initialize() {
		CountryService countryService = new CountryService();
		countries = countryService.getCountries();
	}

	@Test
	public void serverAvailabilityTest() {
		assertTrue(new CountryService().isServiceAvailable());
	}
	
	@Test
	public void existsArgentinaTest() {
		assertTrue(countries.stream().anyMatch(
				country -> country.getName().equalsIgnoreCase(TestData.ARGENTINA_COUNTRY_NAME)));
	}
	
	@Test
	public void existsUruguayTest() {
		assertTrue(countries.stream().anyMatch(
				country -> country.getName().equalsIgnoreCase(TestData.URUGUAY_COUNTRY_NAME)));
	}
	
	@Test
	public void existsBrazilTest() {
		assertTrue(countries.stream().anyMatch(
				country -> country.getName().equalsIgnoreCase(TestData.BRAZIL_COUNTRY_NAME)));
	}
	
	@Test
	public void existsSpainTest() {
		assertTrue(countries.stream().anyMatch(
				country -> country.getName().equalsIgnoreCase(TestData.SPAIN_COUNTRY_NAME)));
	}
}
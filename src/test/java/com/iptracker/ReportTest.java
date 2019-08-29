package com.iptracker;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iptracker.entities.CountryReport;
import com.iptracker.repositories.CountryRepository;
import com.iptracker.services.CountryService;

public class ReportTest {
	
	private static CountryService countryService;

	@BeforeClass
	public static void initialize() {
		countryService = new CountryService();
		CountryRepository.getInstance().setCountries(countryService.getCountries());
	}
	
	@Test
	public void furthestDistanceToArgentinaReportTest() {
		CountryRepository.getInstance().getCountryByISOCode(TestData.BRAZIL_ISO_CODE);
		CountryRepository.getInstance().getCountryByISOCode(TestData.URUGUAY_ISO_CODE);
		CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);

		assertEquals(TestData.SPAIN_COUNTRY_NAME, new CountryReport().getFurthestCountryToArgentina().getName());
	}
	
	@Test
	public void closestDistanceToArgentinaReportTest() {
		CountryRepository.getInstance().getCountryByISOCode(TestData.BRAZIL_ISO_CODE);
		CountryRepository.getInstance().getCountryByISOCode(TestData.URUGUAY_ISO_CODE);
		CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);

		assertEquals(TestData.URUGUAY_COUNTRY_NAME, new CountryReport().getClosestCountryToArgentina().getName());
	}
	
	@Test
	public void averageDistanceReportTest() {
		for (int i = 0; i < 10; i++) {
			CountryRepository.getInstance().getCountryByISOCode(TestData.BRAZIL_ISO_CODE);
		}
		for (int i = 0; i < 5; i++) {
			CountryRepository.getInstance().getCountryByISOCode(TestData.SPAIN_ISO_CODE);
		}
		assertEquals(5254, new CountryReport().getAverageDistance(), 80);
	}
	
	@After
	public void reset() {
		CountryRepository.getInstance().resetCountriesConsultedCount();
	}
}
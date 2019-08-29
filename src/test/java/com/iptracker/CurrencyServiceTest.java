package com.iptracker;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iptracker.entities.Currency;
import com.iptracker.services.CurrencyService;

public class CurrencyServiceTest {

	private static List<Currency> currencies;
	
	@BeforeClass
	public static void initialize() {
		CurrencyService currencyService = new CurrencyService();
		currencies = currencyService.getCurrencies();
	}
	
	@Test
	public void serverAvailabilityTest() {
		assertTrue(new CurrencyService().isServiceAvailable());
	}
	
	@Test
	public void existsUSDollarCurrencyTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.US_DOLLAR_CURRENCY_CODE)));
	}
	
	@Test
	public void existsArgentinePesoCurrencyTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.ARGENTINE_PESO_CURRENCY_CODE)));
	}
	
	@Test
	public void existsEuroCurrencyTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.EURO_CURRENCY_CODE)));
	}
	
	@Test
	public void existsUSDollarRateTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.US_DOLLAR_CURRENCY_CODE) && currency.getRate() > 0));
	}
	
	@Test
	public void existsArgentinePesoRateTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.ARGENTINE_PESO_CURRENCY_CODE) && currency.getRate() > 0));
	}
	
	@Test
	public void existsEuroRateTest() {
		assertTrue(currencies.stream().anyMatch(
				currency -> currency.getCode().equalsIgnoreCase(TestData.EURO_CURRENCY_CODE) && currency.getRate() > 0));
	}
}
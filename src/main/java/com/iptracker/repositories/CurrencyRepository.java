package com.iptracker.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iptracker.entities.Currency;

public class CurrencyRepository {
	
	private static CurrencyRepository instance;
	
	private Map<String, Double> currencies;
	
	private CurrencyRepository() {
		currencies = new HashMap<String, Double>();
	}
	
	public static CurrencyRepository getInstance() {
		if(instance == null) {
			instance = new CurrencyRepository();
		}
		return instance;
	}
	
	public void setCurrencies(List<Currency> currencies) {
		currencies.forEach(
				currency -> this.currencies.put(currency.getCode(), currency.getRate()));
	}
	
	public void modifyBaseCurrencyTo(String currencyCode) {
		double baseCurrencyRate = currencies.get(currencyCode);
		currencies.forEach(
				(code, rate) -> currencies.put(code, 1 / (rate / baseCurrencyRate)));
	}
	
	public double getCurrencyRateFromCurrencyCode(String currencyCode) {
		return currencies.get(currencyCode);
	}
}
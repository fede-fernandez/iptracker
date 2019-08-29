package com.iptracker.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.iptracker.repositories.CurrencyRepository;
import com.iptracker.server.Configuration;
import com.iptracker.services.CurrencyService;

public class CurrencyScheduler {
	
	private ScheduledExecutorService scheduler;
	
	private String baseCurrency;
	
	private long delay;
	
	public CurrencyScheduler() {
		scheduler = Executors.newSingleThreadScheduledExecutor();
		delay = Configuration.getInstance().getLongPropertyValue("currency.scheduler.task.every.x.minutes");
		baseCurrency = Configuration.getInstance().getStringPropertyValue("currency.service.base.currency");
	}
	
	public void initiateCurrencyScheduler() {
		updateCurrencies();
		scheduler.scheduleWithFixedDelay(() -> updateCurrencies(), delay, delay, TimeUnit.MINUTES);
	}
	
	public void updateCurrencies() {
		CurrencyService currencyService = new CurrencyService();
		CurrencyRepository.getInstance().setCurrencies(currencyService.getCurrencies());
		CurrencyRepository.getInstance().modifyBaseCurrencyTo(baseCurrency);
	}
}
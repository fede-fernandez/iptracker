package com.iptracker.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Country {
	
	private String name;
	
	private String isoCode;
	
	private List<Language> languages;
	
	private List<Currency> currencies;
	
	private List<String> timezones;
	
	private Coordinates coordinates;
	
	private long timesConsultedCount;
	
	public Country() {
		languages = new ArrayList<Language>();
		currencies = new ArrayList<Currency>();
		timezones = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
	public void addLanguage(Language language) {
		this.languages.add(language);
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	
	public void addCurrency(Currency currency) {
		this.currencies.add(currency);
	}

	public List<String> getTimezones() {
		return timezones;
	}

	public void setTimezones(List<String> timezones) {
		this.timezones = timezones;
	}
	
	public void addTimezone(String timezone) {
		this.timezones.add(timezone);
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public long getTimesConsultedCount() {
		return timesConsultedCount;
	}

	public void setTimesConsultedCount(long timesConsultedCount) {
		this.timesConsultedCount = timesConsultedCount;
	}

	public void increaseTimesConsultedCount() {
		this.timesConsultedCount++;
	}

	public List<String> getTimes() {
		return timezones.stream().map(timezone -> LocalDateTime.now(ZoneId.of(timezone))
				.format(DateTimeFormatter.ofPattern("HH:mm:ss"))+ " (" + timezone + ")")
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", isoCode=" + isoCode + ", languages=" + languages + ", currencies="
				+ currencies + ", timezones=" + timezones + ", coordinates=" + coordinates + ", timesConsultedCount="
				+ timesConsultedCount + "]";
	}
}
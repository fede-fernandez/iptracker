package com.iptracker.entities;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.iptracker.repositories.CountryRepository;

public class CountryReport {
	
	private List<Country> consultedCountries;
	
	public CountryReport() {
		consultedCountries = CountryRepository.getInstance().getCountries().stream().filter(country -> 
			country.getTimesConsultedCount() > 0).collect(Collectors.toList());
	}
	
	public List<Country> getConsultedCountries() {
		return consultedCountries;
	}
	
	public List<Country> getConsultedCountriesWithCoordinates() {
		return consultedCountries.stream().filter(country -> 
			country.getCoordinates() != null).collect(Collectors.toList());
	}
	
	public Country getFurthestCountryToArgentina() {
		return getConsultedCountriesWithCoordinates().stream().max(Comparator.comparingDouble(country -> 
			country.getCoordinates().getDistanceToArgentina())).get(); 
	}
	
	public Country getClosestCountryToArgentina() {
		return getConsultedCountriesWithCoordinates().stream().min(Comparator.comparingDouble(country -> 
			country.getCoordinates().getDistanceToArgentina())).get();
	}
	
	public long getAverageDistance() {
		long sumOfDistanceToArgentinaMultipliedByConsultedCount = 
			getConsultedCountriesWithCoordinates().stream().mapToLong(country -> 
				Math.round(country.getCoordinates().getDistanceToArgentina()) * country.getTimesConsultedCount()).sum();

		long consultedCountriesCount = getConsultedCountriesWithCoordinates().stream().mapToLong(country ->
			country.getTimesConsultedCount()).sum();

		long averageDistance = sumOfDistanceToArgentinaMultipliedByConsultedCount / consultedCountriesCount;
		
		return averageDistance;
	}
}
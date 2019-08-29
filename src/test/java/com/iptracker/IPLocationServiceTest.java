package com.iptracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iptracker.exceptions.InvalidIPException;
import com.iptracker.services.IPLocationService;

public class IPLocationServiceTest {
	
	private static IPLocationService ipLocationService;
	
	@BeforeClass
	public static void initialize() {
		ipLocationService = new IPLocationService();
	}
	
	@Test
	public void serverAvailabilityTest() {
		assertTrue(ipLocationService.isServiceAvailable(TestData.AMERICAN_IP));
	}

	@Test
	public void unitedStatesIPTraceTest() {
		assertEquals(TestData.UNITED_STATES_ISO_CODE, ipLocationService.getCountryCodeFromIP(TestData.AMERICAN_IP));
	}
	
	@Test
	public void argentinanIPTraceTest() {
		assertEquals(TestData.ARGENTINA_ISO_CODE, ipLocationService.getCountryCodeFromIP(TestData.ARGENTINIAN_IP));
	}
	
	@Test
	public void spanishIPTraceTest() {
		assertEquals(TestData.SPAIN_ISO_CODE, ipLocationService.getCountryCodeFromIP(TestData.SPANISH_IP));
	}
	
	@Test
	public void brazilianIPTraceTest() {
		assertEquals(TestData.BRAZIL_ISO_CODE, ipLocationService.getCountryCodeFromIP(TestData.BRAZILIAN_IP));
	}
	
	@Test
	public void uruguayanIPTraceTest() {
		assertEquals(TestData.URUGUAY_ISO_CODE, ipLocationService.getCountryCodeFromIP(TestData.URUGUAYAN_IP));
	}
	
	@Test(expected = InvalidIPException.class)
	public void localNetworkIPTraceTest() {
		ipLocationService.getCountryCodeFromIP(TestData.LOCAL_ADDRESS_IP);
	}
}
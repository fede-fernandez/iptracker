package com.iptracker.server;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.LoggerFactory;

public class Configuration {

	private static Configuration instance;

	private Properties properties;
	
	private Configuration () {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		}
		catch (IOException ioException) {
			 LoggerFactory.getLogger(this.getClass()).error("application.properties is missing / could not be read.");
		}
	}
	
	public static Configuration getInstance() {
		if(instance == null) {
			instance = new Configuration();
		}
		return instance;
	}
	
	public String getStringPropertyValue(String propertyKey) {
		return properties.getProperty(propertyKey);
	}
	
	public int getIntegerPropertyValue(String propertyKey) {
		return Integer.valueOf(properties.getProperty(propertyKey));
	}
	
	public long getLongPropertyValue(String propertyKey) {
		return Long.valueOf(properties.getProperty(propertyKey));
	}
}
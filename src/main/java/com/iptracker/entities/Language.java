package com.iptracker.entities;

public class Language {
	
	private String isoCode;
	
	private String name;
	
	public Language(String isoCode, String name) {
		this.isoCode = isoCode;
		this.name = name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Language [isoCode=" + isoCode + ", name=" + name + "]";
	}
}
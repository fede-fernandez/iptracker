package com.iptracker.entities;

public class Currency {
	
	private String code;
	
	private double rate;
	
	public Currency(String code) {
		this.code = code;
	}
	
	public Currency(String code, double rate) {
		this.code = code;
		this.rate = rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Currency [code=" + code + ", rate=" + rate + "]";
	}
}
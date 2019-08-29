package com.iptracker.entities;

public class Coordinates {
	
	private double latitude;
	
	private double longitude;
	
	public final static double ARGENTINIAN_LATITUDE = -34.0;
	public final static double ARGENTINIAN_LONGITUDE = -64.0;

	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double distanceFromAnotherCoordinateInKilometers(Coordinates anotherCoordinates) {
		double earthRadius = 6371;
		double deltaLatitude = Math.toRadians(anotherCoordinates.getLatitude() - latitude);
		double deltaLongitude = Math.toRadians(anotherCoordinates.getLongitude() - longitude);
		double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2)
				+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(anotherCoordinates.getLatitude()))
						* Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);
		double angularDistance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRadius * angularDistance;
	}
	
	public double getDistanceToArgentina() {
		return distanceFromAnotherCoordinateInKilometers(new Coordinates(ARGENTINIAN_LATITUDE, ARGENTINIAN_LONGITUDE));
	}
	
	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
package com.cooksys.dto;

import java.util.ArrayList;

import com.cooksys.pojo.Flight;

public class ItineraryInputDto {
	private String userName;
	
	private ArrayList<Flight> flights;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

}

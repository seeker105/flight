package com.cooksys.dto;

import java.util.ArrayList;

import com.cooksys.pojo.Flight;

public class FlightsDto {
	
	ArrayList<Flight> flights;

	public FlightsDto() {
		super();
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	
	

}

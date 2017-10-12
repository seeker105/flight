package com.cooksys.dto;

import java.util.ArrayList;

import com.cooksys.entity.Location;
import com.cooksys.pojo.Flight;

public class ItineraryDto {
	private long id;
	
	private Location origin;
	
	private Location destination;
	
	private String userName;
	
	private ArrayList<Flight> flights;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ItineraryDto)) {
			return false;
		}
		ItineraryDto other = (ItineraryDto) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ItineraryDto [id=" + id + ", origin=" + origin + ", destination=" + destination + ", userName="
				+ userName + ", flights=" + flights + "]";
	}

}
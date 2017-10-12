package com.cooksys.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.cooksys.entity.Itinerary;

//@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private long id;

	//Name of city where flight originates
	private String origin;
	
	//Name of city where flight lands
	private String destination;
	
	//How many hours flight is in the air
	private long flightTime;
	
	//How many hours after the start of the day until the flight takes off
	private long offsetFromOpen;
	
	private int originLat;
	private int originLng;
	private int destinationLat;
	private int destinationLng;
	
	@ManyToOne
	private Itinerary itinerary;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public long getFlightTime() {
		return flightTime;
	}
	public void setFlightTime(long flightTime) {
		this.flightTime = flightTime;
	}
	public long getOffset() {
		return offsetFromOpen;
	}
	public void setOffset(long offset) {
		this.offsetFromOpen = offset;
	}
	public Flight(String origin, String destination, long flightTime, long offset) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.offsetFromOpen = offset;
	}
	
	public Flight(String origin, String destination, long flightTime, long offset, int originLat, int originLng,
			int destinationLat, int destinationLng) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightTime = flightTime;
		this.offsetFromOpen = offset;
		this.originLat = originLat;
		this.originLng = originLng;
		this.destinationLat = destinationLat;
		this.destinationLng = destinationLng;
	}
	public Flight() {
		super();
	}
	
	
	

}

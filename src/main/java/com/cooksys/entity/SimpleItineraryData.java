package com.cooksys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SimpleItineraryData {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Client traveller;
	
	private String itineraryString;
	
	public SimpleItineraryData() {
		super();
	}

	public SimpleItineraryData(String itineraryString, Client traveller) {
		super();
		this.traveller = traveller;
		this.itineraryString = itineraryString;
	}

	public Client getTraveller() {
		return traveller;
	}

	public void setTraveller(Client traveller) {
		this.traveller = traveller;
	}

	public String getItineraryString() {
		return itineraryString;
	}

	public void setItineraryString(String itineraryString) {
		this.itineraryString = itineraryString;
	}

	@Override
	public String toString() {
		return "SimpleItineraryData [traveller=" + traveller + ", itineraryString=" + itineraryString + "]";
	}
	
	
	
}

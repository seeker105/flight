package com.cooksys.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooksys.pojo.Flight;

//@Entity
@Table(name="Itinerary")
public class Itinerary {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private Client traveller;
	
	@OneToMany(mappedBy = "itinerary")
	private List<Flight> flights;
	
	public Itinerary() {
		super();
	}

	public Itinerary(List<Flight> flights, Client traveller) {
		super();
		this.traveller = traveller;
		this.flights = flights;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public Client getTraveller() {
		return traveller;
	}

	public void setTraveller(Client traveller) {
		this.traveller = traveller;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		if (!(obj instanceof Itinerary)) {
			return false;
		}
		Itinerary other = (Itinerary) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Itinerary [id=" + id + ", traveller=" + traveller + "]";
	}
	
	
	
	

}

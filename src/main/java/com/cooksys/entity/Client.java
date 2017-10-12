package com.cooksys.entity;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cooksys.embedded.Credentials;
import com.cooksys.embedded.Profile;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String userName;
	
	@Embedded
	private Profile profile;

	@Embedded
	private Credentials credentials;
	
//	@OneToMany(mappedBy="traveller")
//	private List<Itinerary> itineraries;
	
	@OneToMany(mappedBy="traveller")
	private List<SimpleItineraryData> simpleItineraryStrings;
	
	public Client() {
		super();
	}
	
	public Client(Credentials credentials, Profile profile) {
		super();
		this.userName = credentials.getUserLogin();
		this.credentials = credentials;
		this.profile = profile;
	}

//	public List<Itinerary> getItineraries() {
//		return itineraries;
//	}
//
//	public void setItineraries(List<Itinerary> itineraries) {
//		this.itineraries = itineraries;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", userName=" + userName + ", profile=" + profile + "]";
	}


	
	

}

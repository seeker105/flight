package com.cooksys.dto;

import javax.persistence.Embedded;

import com.cooksys.embedded.Profile;

public class ClientDto {
	private long id;
	
	@Embedded
	private Profile profile;
	
	private String userName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		if (!(obj instanceof ClientDto)) {
			return false;
		}
		ClientDto other = (ClientDto) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ClientDto [id=" + id + ", profile=" + profile + ", userName=" + userName + "]";
	}


	
	
}

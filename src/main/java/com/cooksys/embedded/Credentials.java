package com.cooksys.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Credentials {
	
	private String userLogin;
	private String password;
	
	public String getUserLogin() {
		return userLogin;
	}
	
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
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
		if (!(obj instanceof Credentials)) {
			return false;
		}
		Credentials other = (Credentials) obj;
		if (userLogin == null) {
			if (other.userLogin != null) {
				return false;
			}
		} else if (!userLogin.equals(other.userLogin)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Credentials [userLogin=" + userLogin + ", password=" + password + "]";
	}
	
	
	

}

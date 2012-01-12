package org.varkrs.sociality.local.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnore;

@SuppressWarnings("serial")
@Embeddable
public class Login implements Serializable{
	@Column(unique=true, nullable=false)
	private String userName;
	
	@JsonIgnore
	private String password;

	protected Login() {}
	
	public Login(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + "]";
	}
	
	
}

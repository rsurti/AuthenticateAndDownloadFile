package com.cbnits.authapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cbnits-154
 *
 */
@Entity
@Table(name = "UserData")
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String passkey;

	public UserData() {

	}

	public UserData(String username, String k) {
		super();
		this.username = username;
		this.passkey = k;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getKey() {
		return passkey;
	}

	public void setKey(String key) {
		this.passkey = key;
	}

}

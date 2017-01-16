package com.ifi.web.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = -457758713333058716L;

	private String username;
	private String password;
	private ProfileDto profil;

	public ProfileDto getProfil() {
		return profil;
	}

	public void setProfil(ProfileDto profil) {
		this.profil = profil;
	}

	public UserDto() {
		super();
	}

	public UserDto(final String userName, final String password) {
		this.username = userName;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
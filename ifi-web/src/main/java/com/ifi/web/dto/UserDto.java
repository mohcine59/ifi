package com.ifi.web.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto implements Serializable {

	private static final long serialVersionUID = -457758713333058716L;

	private String nom;
	private String prenom;
	private String userName;
	private String password;

	public UserDto() {
		super();
	}

	public UserDto(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
		this.nom = "";
		this.prenom = "";
	}

	public UserDto(final String userName, final String nom, final String prenom) {
		this.userName = userName;
		this.nom = nom;
		this.prenom = prenom;
		this.password = "";
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(final String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(final String prenom) {
		this.prenom = prenom;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
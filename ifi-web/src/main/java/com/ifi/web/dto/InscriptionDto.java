package com.ifi.web.dto;

import java.io.Serializable;

public class InscriptionDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2287173440030724717L;

	private Long id;
	
	private String username;
	
	private String password;

	private String email;

	private String phone;

	private String competences;

	private String projects;

	private String twitterId;

	private String facebookId;

	private String linkedinId;

	public String picture;

	public InscriptionDto() {
		super();
	}
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public InscriptionDto(final String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getCompetences() {
		return this.competences;
	}

	public void setCompetences(final String competences) {
		this.competences = competences;
	}

	public String getProjects() {
		return this.projects;
	}

	public void setProjects(final String projects) {
		this.projects = projects;
	}

	public String getTwitterId() {
		return this.twitterId;
	}

	public void setTwitterId(final String twitterId) {
		this.twitterId = twitterId;
	}

	public String getFacebookId() {
		return this.facebookId;
	}

	public void setFacebookId(final String facebookId) {
		this.facebookId = facebookId;
	}

	public String getLinkedinId() {
		return this.linkedinId;
	}

	public void setLinkedinId(final String linkedinId) {
		this.linkedinId = linkedinId;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

}
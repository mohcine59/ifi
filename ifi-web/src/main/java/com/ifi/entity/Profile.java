package com.ifi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ifi.web.dto.ProfileDto;

@Entity
@Table(name = "profil")
public class Profile implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2327635312463701785L;

	@Id
	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = true)
	private String email;

	@Column(name = "phone", nullable = true)
	private String phone;

	@Column(name = "competences", nullable = true)
	private String competences;

	@Column(name = "projects", nullable = true)
	private String projects;

	@Column(name = "twitterId", nullable = true)
	private String twitterId;

	@Column(name = "facebookId", nullable = true)
	private String facebookId;

	@Column(name = "linkedinId", nullable = true)
	private String linkedinId;

	@Column(name = "picture", nullable = true)
	private String picture;

	public Profile() {
		super();
	}

	public Profile(final String username) {
		this();
		this.username = username;
	}

	public Profile(final ProfileDto profilDto) {
		this();
		this.username = profilDto.getUsername();
		this.email = profilDto.getEmail();
		this.phone = profilDto.getPhone();
		this.competences = profilDto.getCompetences();
		this.projects = profilDto.getProjects();
		this.facebookId = profilDto.getFacebookId();
		this.twitterId = profilDto.getTwitterId();
		this.linkedinId = profilDto.getLinkedinId();
		this.picture = profilDto.getLinkedinId();
	}

	public Profile(final String email, final String phone, final String competences) {
		this();
		this.email = email;
		this.phone = phone;
		this.competences = competences;
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
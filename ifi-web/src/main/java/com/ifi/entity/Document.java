package com.ifi.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Documents")
public class Document implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1851169280104231900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "filename", nullable = false)
	private String filename;

	@Column(name = "uuid", nullable = false)
	private String uuid;

	public Document() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}

	public Document(final Integer id, final String username, final String filename) {
		this();
		this.id = id;
		this.username = username;
		this.filename = filename;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(final String filename) {
		this.filename = filename;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
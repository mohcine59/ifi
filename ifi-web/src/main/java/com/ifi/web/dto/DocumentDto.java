package com.ifi.web.dto;

import java.io.Serializable;
import java.util.UUID;

public class DocumentDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1851169280104231900L;

	private Integer id;

	private String username;

	private String filename;

	private String uuid;

	public DocumentDto() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}

	public DocumentDto(final Integer id, final String username, final String filename) {
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
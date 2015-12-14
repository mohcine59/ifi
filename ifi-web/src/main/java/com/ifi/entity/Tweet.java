package com.ifi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ifi.web.dto.TweetDto;

@Entity
@Table(name = "tweet")
public class Tweet implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1851169280104231900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "tweet", nullable = false)
	private String tweet;

	@Column(name = "date", nullable = true)
	private Date date;

	public Tweet() {
		super();
	}

	public Tweet(final TweetDto tweetDto) {
		this();
		this.username = tweetDto.getUsername();
		this.tweet = tweetDto.getTweet();
	}

	public Tweet(final String username, final String tweet) {
		this();
		this.username = username;
		this.tweet = tweet;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getTweet() {
		return this.tweet;
	}

	public void setTweet(final String tweet) {
		this.tweet = tweet;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

}
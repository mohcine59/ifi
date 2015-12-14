package com.ifi.web.dto;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TweetDto implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7324895091479752932L;

	private String username;

	private String tweet;

	private Duration elapseTime;

	private final List<String> hashTags = new ArrayList<>(0);

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

	public Duration getElapseTime() {
		return this.elapseTime;
	}

	public void setElapseTime(final Duration elapseTime) {
		this.elapseTime = elapseTime;
	}

	public String getElapsedTimeFormated() {
		final long days = Math.abs(this.elapseTime.toDays());
		final long hours = Math.abs(this.elapseTime.toHours());
		final long minutes = Math.abs(this.elapseTime.toMinutes());
		final long seconds = Math.abs(this.elapseTime.getSeconds());
		if (days > 0) {
			return days + (days > 1 ? " days" : " day");
		} else if (hours > 0) {
			return hours + (hours > 1 ? " hours" : " hour");
		} else if (minutes > 0) {
			return minutes + (minutes > 1 ? " minutes" : " minute");
		} else {
			return seconds + (seconds > 1 ? " seconds" : " second");
		}
	}

	public List<String> getHashTags() {
		if (this.hashTags.isEmpty()) {
			final String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
			final Pattern pattern = Pattern.compile(patternStr);
			final Matcher matcher = pattern.matcher(this.tweet);
			String tag = "";

			while (matcher.find()) {
				tag = matcher.group();
				tag = tag.replace(" ", "");
				this.hashTags.add(tag);
			}
		}

		return this.hashTags;
	}
}
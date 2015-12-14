package com.ifi.service;

import java.util.List;

import com.ifi.web.dto.TweetDto;

public interface TweetService {

	public TweetDto addTweet(TweetDto tweetDto);

	public List<TweetDto> getLastTweets();

	public List<TweetDto> getTweetsByHastag(final String hashTag);

	public List<TweetDto> getTweetsByUsername(final String username);

}
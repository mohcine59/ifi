package com.ifi.dao;

import java.util.List;

import com.ifi.entity.Tweet;

public interface TweetDao extends AbstractDao {

	List<Tweet> getLastTweets();

	List<Tweet> getTweetsByHastag(String hashTag);

	List<Tweet> getTweetsByUsername(String username);

}
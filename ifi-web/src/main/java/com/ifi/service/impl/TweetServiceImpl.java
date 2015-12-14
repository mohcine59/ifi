package com.ifi.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifi.dao.TweetDao;
import com.ifi.entity.Tweet;
import com.ifi.service.TweetService;
import com.ifi.web.dto.TweetDto;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetDao tweetDao;

	@Override
	public TweetDto addTweet(final TweetDto TweetDto) {
		final Tweet tweet = new Tweet(TweetDto);
		tweet.setDate(new Date());

		return new ModelMapper().map(this.tweetDao.add(tweet), TweetDto.class);
	}

	@Override
	public List<TweetDto> getLastTweets() {
		final List<Tweet> lastTweets = this.tweetDao.getLastTweets();
		List<TweetDto> lastTweetsDTO = null;
		if ((lastTweets != null) && (lastTweets.size() > 0)) {
			lastTweetsDTO = convetTweetToDto(lastTweets);
		}
		return lastTweetsDTO;
	}

	@Override
	public List<TweetDto> getTweetsByHastag(final String hashTag) {
		final List<Tweet> listTweets = this.tweetDao.getTweetsByHastag(hashTag);
		List<TweetDto> listTweetsDTO = null;
		if ((listTweets != null) && (listTweets.size() > 0)) {
			listTweetsDTO = convetTweetToDto(listTweets);
		}
		return listTweetsDTO;
	}

	private static List<TweetDto> convetTweetToDto(final List<Tweet> listTweets) {
		final List<TweetDto> listTweetDTO = new ArrayList<TweetDto>(listTweets.size());
		for (final Tweet tweet : listTweets) {
			final TweetDto dto = new ModelMapper().map(tweet, TweetDto.class);
			final Duration elapsedTime = Duration.between(Instant.now(), tweet.getDate().toInstant());
			dto.setElapseTime(elapsedTime);
			listTweetDTO.add(dto);
		}

		return listTweetDTO;
	}

	@Override
	public List<TweetDto> getTweetsByUsername(final String username) {
		final List<Tweet> listTweets = this.tweetDao.getTweetsByUsername(username);
		List<TweetDto> listTweetsDTO = null;
		if ((listTweets != null) && (listTweets.size() > 0)) {
			listTweetsDTO = convetTweetToDto(listTweets);
		}
		return listTweetsDTO;
	}

}
package com.ifi.webservice;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.dao.TweetDao;
import com.ifi.entity.Tweet;
import com.ifi.web.dto.TweetDto;

@Controller
@RequestMapping(value = "/tweetService", produces = "application/json")
public class TweetWebService {

	@Autowired
	TweetDao tweetDao;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TweetDto> addTweet(@RequestBody final TweetDto TweetDto) {
		final Tweet tweet = new Tweet(TweetDto);
		tweet.setDate(new Date());

		final TweetDto dto = new ModelMapper().map(this.tweetDao.add(tweet), TweetDto.class);

		return new ResponseEntity<TweetDto>(dto, HttpStatus.OK);
	}

	@RequestMapping(value = "/last-tweets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<TweetDto>> getLastTweets() {
		final List<Tweet> lastTweets = this.tweetDao.getLastTweets();
		List<TweetDto> lastTweetsDTO = null;
		if ((lastTweets != null) && (lastTweets.size() > 0)) {
			lastTweetsDTO = convetTweetToDto(lastTweets);
		}
		return new ResponseEntity<Iterable<TweetDto>>(lastTweetsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/tweets-by-hashtag/{tag}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<TweetDto>> getTweetsByHastag(@PathVariable final String hashTag) {
		final List<Tweet> listTweets = this.tweetDao.getTweetsByHastag(hashTag);
		List<TweetDto> listTweetsDTO = null;
		if ((listTweets != null) && (listTweets.size() > 0)) {
			listTweetsDTO = convetTweetToDto(listTweets);
		}
		return new ResponseEntity<Iterable<TweetDto>>(listTweetsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/tweets-by-user/{username}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<TweetDto>> getTweetsByUsername(@PathVariable final String username) {
		final List<Tweet> listTweets = this.tweetDao.getTweetsByUsername(username);
		List<TweetDto> listTweetsDTO = null;
		if ((listTweets != null) && (listTweets.size() > 0)) {
			listTweetsDTO = convetTweetToDto(listTweets);
		}
		return new ResponseEntity<Iterable<TweetDto>>(listTweetsDTO, HttpStatus.OK);
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

}
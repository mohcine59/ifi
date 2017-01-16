package com.ifi.web.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ifi.service.TweetService;
import com.ifi.web.Tools;
import com.ifi.web.dto.TweetDto;

@Controller
public class TweetController {

	@Autowired
	private TweetService tweetService;

	@RequestMapping(value = "/tweet", method = RequestMethod.GET)
	public String getLastTweets(final Model model, final Principal principal) {
		System.out.println("principal");
		System.out.println(principal.getName());
		final List<TweetDto> lastTweets = this.tweetService.getLastTweets();
		Tools.replaceUserWithHTML(lastTweets);
		model.addAttribute("currentUser", principal.getName());
		model.addAttribute("listTweets", lastTweets);

		return "tweet";
	}

	@Transactional
	@RequestMapping(value = "/add-tweet", method = RequestMethod.POST)
	public String addTweet(@ModelAttribute("tweet") final TweetDto dto, final Principal principal) {
		dto.setUsername(principal.getName());
		this.tweetService.addTweet(dto);

		return "redirect:/tweet";
	}

	@RequestMapping(value = "/hashtag/{hashTag}", method = RequestMethod.GET)
	public String getTweetsByHastag(final Model model, @PathVariable final String hashTag) {

		final List<TweetDto> listTweets = this.tweetService.getTweetsByHastag(hashTag);
		Tools.replaceUserWithHTML(listTweets);

		model.addAttribute("currentHashtag", hashTag);
		model.addAttribute("listTweets", listTweets);

		return "hashTag";
	}
}
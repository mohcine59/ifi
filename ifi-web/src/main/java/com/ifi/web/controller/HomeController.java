package com.ifi.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifi.service.TweetService;
import com.ifi.web.Tools;
import com.ifi.web.dto.TweetDto;

@Controller
public class HomeController {

	@Autowired
	private TweetService tweetService;

	@RequestMapping(value = { "", "/", "/home" })
	public String goHome(final Model model) {

		final List<TweetDto> lastTweets = this.tweetService.getLastTweets();
		Tools.replaceUserWithHTML(lastTweets);

		model.addAttribute("listTweets", lastTweets);

		return "home";
	}

}
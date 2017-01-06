package com.ifi.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ifi.web.dto.TweetDto;
import com.ifi.web.dto.UserDto;

public class Tools {

	public static void replaceUserWithHTML(final List<TweetDto> listTweets) {
		final List<String> listUser = getListUsername();
		if ((listTweets != null) && (listUser != null)) {
			for (final TweetDto tweetDto : listTweets) {
				final String patternStr = "(?:\\s|\\A)[@]+([A-Za-z0-9-_]+)";
				final Pattern pattern = Pattern.compile(patternStr);
				String tweet = tweetDto.getTweet();
				final Matcher matcher = pattern.matcher(tweet);
				while (matcher.find()) {
					String result = matcher.group();
					result = result.replace(" ", "");
					final String username = result.replace("@", "");
					if (listUser.contains(username.toLowerCase())) {
						final String userHTML = "<a href=/ifi-web/profile/" + username + ">" + result + "</a>";
						tweet = tweet.replace(result, userHTML);
					}
					tweetDto.setTweet(tweet);
				}
			}
		}
	}

	public static List<String> getListUsername() {
		try {
			final RestTemplate restTemplate = getClientRest();

			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("xSessionId", getSessionId("admin", "admin"));

			final HttpEntity<Object> entity = new HttpEntity<Object>("", headers);

			final String url = "http://localhost:9090/employee";
			final ResponseEntity<UserDto[]> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					UserDto[].class);

			final UserDto[] tabUser = response.getBody();
			if ((tabUser != null) && (tabUser.length > 0)) {
				final List<String> listUsername = new ArrayList<>(tabUser.length);
				for (final UserDto userJson : tabUser) {
					listUsername.add(userJson.getUserName().toLowerCase());
				}

				return listUsername;
			}

			return null;

		} catch (final RestClientException e) {
			return null;
		}
	}

	public static boolean isUserExist(final String username) {
		return getListUsername().contains(username.toLowerCase());
	}

	public static String getSessionId(final String username, final String password) throws AuthenticationException {
		final RestTemplate restTemplate = getClientRest();

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		final HttpEntity<Object> entity = new HttpEntity<Object>(new UserDto(username, password), headers);

		final ResponseEntity<String> out = restTemplate.exchange("http://localhost:9090/session", HttpMethod.POST,
				entity, String.class);

		if (out.getStatusCode() == HttpStatus.OK) {
			return out.getHeaders().get("xSessionId").get(0);
		}

		return null;
	}

	public static RestTemplate getClientRest() {
		final RestTemplate restTemplate = new RestTemplate();
		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}

}

package com.ifi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.ifi.entity.User;
import com.ifi.service.UserService;
import com.ifi.web.dto.UserDto;

@Component
public class AuthenticateService implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String login = authentication.getName();
		final String pwd = authentication.getCredentials().toString();

		try {
			final UserDto userDto = this.userService.login(login, pwd);
			if (userDto != null) {
				final List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(new SimpleGrantedAuthority("MEMBRE"));
				final Authentication auth = new UsernamePasswordAuthenticationToken(login, pwd, grantedAuths);

				return auth;
			} else {
				throw new AuthenticationServiceException("Erreur d'Authentification.");
			}

		} catch (final RestClientException e) {
			throw new AuthenticationServiceException("Impossible de s'authentifier.", e);
		}
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

package com.ifi.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class AuthenticateService implements AuthenticationProvider {

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final String login = authentication.getName();
		final String pwd = authentication.getCredentials().toString();

		try {
			final String sessionId = Tools.getSessionId(login, pwd);
			if (sessionId != null) {
				final List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
				final Authentication auth = new UsernamePasswordAuthenticationToken(login, sessionId, grantedAuths);

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

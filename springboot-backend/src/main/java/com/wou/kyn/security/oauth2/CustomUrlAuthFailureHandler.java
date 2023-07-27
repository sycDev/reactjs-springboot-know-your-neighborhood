package com.wou.kyn.security.oauth2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomUrlAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Value("${app.oauth2.redirectUri}")
    private String redirectUri;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String targetUrl = redirectUri.isEmpty() ? "/" : redirectUri;

		// Append exception message to the redirect url
		targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
				.queryParam("error", exception.getLocalizedMessage()).build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}

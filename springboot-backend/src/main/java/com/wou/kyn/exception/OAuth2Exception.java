package com.wou.kyn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class OAuth2Exception extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public OAuth2Exception(String message) {
        super(message);
    }

	public OAuth2Exception(String message, Throwable cause) {
        super(message, cause);
    }
}

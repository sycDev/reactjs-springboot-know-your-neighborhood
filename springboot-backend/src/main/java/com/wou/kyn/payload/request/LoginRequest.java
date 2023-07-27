package com.wou.kyn.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message = "Email or username is required")
	private String emailOrUsername;

	@NotBlank(message = "Password is required")
	private String password;

	public String getEmailOrUsername() {
		return emailOrUsername;
	}

	public void setEmailOrUsername(String emailOrUsername) {
		this.emailOrUsername = emailOrUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

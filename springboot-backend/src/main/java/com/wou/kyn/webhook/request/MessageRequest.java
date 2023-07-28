package com.wou.kyn.webhook.request;

import javax.validation.constraints.NotBlank;

public class MessageRequest {
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotBlank(message = "Text is required")
	private String text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name= name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

package com.wou.kyn.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wou.kyn.annotation.ValidPassword;

public class RegisterRequest {
	@NotBlank(message = "Username is required")
	@Size(max=30)
	private String username;
	
	@NotBlank(message = "Email is required")
	@Size(max = 255, message = "Email must not exceed 255 characters")
	@Pattern(regexp = "^(?!.*[\\s<:,*])(?!.*\\.\\.)[^@\\.][^\\s@]*@[^\\s@]+\\.[^\\s@]{2,}$", message = "Invalid email address")
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
	@ValidPassword
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

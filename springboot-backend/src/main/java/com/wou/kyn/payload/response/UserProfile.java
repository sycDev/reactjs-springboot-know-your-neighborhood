package com.wou.kyn.payload.response;

import java.time.Instant;

public class UserProfile {
	private Long userId;
	private String username;
	private String email;
	private String name;
	private String imgUrl;
	private Instant joinedAt;
	
	public UserProfile() {
		
	}

	public UserProfile(Long userId, String username, String email, String name, String imgUrl, Instant joinedAt) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.name = name;
		this.imgUrl = imgUrl;
		this.joinedAt = joinedAt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Instant getJoinedAt() {
		return joinedAt;
	}

	public void setJoinedAt(Instant joinedAt) {
		this.joinedAt = joinedAt;
	}
}

package com.wou.kyn.payload.response;

public class UserSummary {
	private Long userId;
    private String username;
    private String name;
    private String provider;

    public UserSummary(Long userId, String username, String name, String provider) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.provider = provider;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}

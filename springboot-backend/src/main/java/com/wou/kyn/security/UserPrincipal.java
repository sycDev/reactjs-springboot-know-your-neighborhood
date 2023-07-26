package com.wou.kyn.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wou.kyn.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserPrincipal implements UserDetails, OAuth2User {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String name;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String password;

	private String provider;

	private Collection<? extends GrantedAuthority> authorities;

	private Map<String, Object> attributes;

	public UserPrincipal(Long id, String username, String name, String email, String password, String provider,
			Collection<? extends GrantedAuthority> authorities) {

		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.provider = provider;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

		return new UserPrincipal(user.getUserId(), user.getUsername(), user.getName(), user.getEmail(),
				user.getPassword(), user.getProvider(), authorities);
	}

	public static UserPrincipal create(User user, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(user);
		userPrincipal.setAttributes(attributes);

		return userPrincipal;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public String getProvider() {
		return provider;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		UserPrincipal that = (UserPrincipal) o;

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}

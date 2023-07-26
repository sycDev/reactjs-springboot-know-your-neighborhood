package com.wou.kyn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wou.kyn.entity.audit.DateAudit;

@Entity
@Table(name = "user")
public class User extends DateAudit {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

	@NotBlank(message = "Username is required")
	@Size(max = 30, message = "Username must not exceed 30 characters")
	@Column(nullable = false, unique = true)
	private String username;
	
	@NotBlank(message = "Email is required")
	@Size(max = 255, message = "Email must not exceed 255 characters")
	@Pattern(regexp = "^(?!.*[\\s<:,*])(?!.*\\.\\.)[^@\\.][^\\s@]*@[^\\s@]+\\.[^\\s@]{2,}$", message = "Invalid email address")
	@Column(nullable = false, unique = true)
	private String email;

	@Size(min = 8, max = 64, message = "Password must be between 8 and 64 characters")
	private String password;

	private String name;
	
	private String imgUrl;

	@NotBlank(message = "Provider is required")
	@Size(max = 20, message = "Provider must not exceed 20 characters")
	@Column(nullable = false)
	private String provider;

	@Column(name = "provider_id")
	private String providerId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
		
	}

	// For local account
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	// For social login account
	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public User(Long userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}

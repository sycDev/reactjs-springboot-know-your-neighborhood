package com.wou.kyn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wou.kyn.entity.User;
import com.wou.kyn.exception.ResourceNotFoundException;
import com.wou.kyn.payload.response.UserProfile;
import com.wou.kyn.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repository;

	/**
	 * Checks if is username already exists
	 *
	 * @param username the username
	 * @return the boolean
	 */
	public Boolean isUsernameExists(String username) {
		return repository.existsByUsername(username);
	}
	
	/**
	 * Checks if is email already exists
	 *
	 * @param email the email
	 * @return the boolean
	 */
	public Boolean isEmailExists(String email) {
		return repository.existsByEmail(email);
	}
	
	/**
	 * Gets all the user profile
	 *
	 * @return the all user profiles
	 */
	public List<UserProfile> getAllUsers() {
		List<User> userList = repository.findAll();
		List<UserProfile> userProfileList = new ArrayList<>();
		
		for (User user : userList) {
			UserProfile userProfile = new UserProfile(user.getUserId(),
					user.getUsername(),
					user.getEmail(),
					user.getName(),
					user.getImgUrl(),
					user.getCreatedAt());

			userProfileList.add(userProfile);
		}

		return userProfileList;
	}

	/**
	 * Get a user by its ID
	 *
	 * @param userId the user id to be found
	 * @return the user profile found
	 */
	public UserProfile getByUserId(Long userId) {
		User user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

		return new UserProfile(user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getName(),
				user.getImgUrl(),
				user.getCreatedAt());
	}

	/**
	 * Search a user by email or username
	 *
	 * @param keyword the searching keyword
	 * @return the user profile
	 */
	public UserProfile searchUser(String keyword) {
		Optional<User> result = repository.findByEmailOrUsername(keyword, keyword);
		if (!result.isPresent()) {
			return null;
		}

		User user = result.get();

		return new UserProfile(user.getUserId(),
				user.getUsername(),
				user.getEmail(),
				user.getName(),
				user.getImgUrl(),
				user.getCreatedAt());
	}

	/**
	 * Delete an existing user
	 * 
	 * @param userId the user id to be deleted
	 */
	public void deleteUser(Long userId) {
		repository.deleteById(userId);
	}
}

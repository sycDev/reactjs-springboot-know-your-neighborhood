package com.wou.kyn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wou.kyn.payload.response.ApiResponse;
import com.wou.kyn.payload.response.UserProfile;
import com.wou.kyn.payload.response.UserSummary;
import com.wou.kyn.security.CurrentUser;
import com.wou.kyn.security.UserPrincipal;
import com.wou.kyn.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private UserService userService;

	/**
	 * Check username already exists or not
	 * 
	 * GET /api/users/checkUsernameExistence?username={username}
	 */
	@GetMapping("/checkUsernameExistence")
    public Boolean checkUsernameExistence(@RequestParam(value = "username") String username) {
        return userService.isUsernameExists(username);
    }

	/**
	 * Check email already exists or not
	 * 
	 * GET /api/users/checkEmailExistence?email={email}
	 */
    @GetMapping("/checkEmailExistence")
    public Boolean checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.isEmailExists(email);
    }

    /**
     * Retrieve the current logged in user
     * 
     * GET /api/users/current
     */
    @GetMapping("/current")
    UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getName(),
                currentUser.getProvider()
        );
    }

    /**
     * Retrieve all users
     * 
     * GET /api/users
     */
    @GetMapping
    ResponseEntity<List<UserProfile>> getAll() {
    	return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieve single user by its id
     * 
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    ResponseEntity<UserProfile> getById(@PathVariable("id") Long userId) {
    	return ResponseEntity.ok(userService.getByUserId(userId));
    }

    /**
     * Search user by email or username
     * 
     * GET /api/users/search?q={keyword}
     */
    @GetMapping("/search")
    ResponseEntity<UserProfile> search(@RequestParam("q") String query) {
    	return ResponseEntity.ok(userService.searchUser(query));
    }

    /**
     * Delete an existing user
     * 
     * DELETE /api/users/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ApiResponse> delete(@PathVariable("id") Long userId) {
    	userService.deleteUser(userId);

    	return ResponseEntity.ok().body(new ApiResponse(true, "User successfully deleted"));
    }
}

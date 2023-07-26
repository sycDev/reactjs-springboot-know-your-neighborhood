package com.wou.kyn.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wou.kyn.entity.User;
import com.wou.kyn.payload.request.LoginRequest;
import com.wou.kyn.payload.request.RegisterRequest;
import com.wou.kyn.payload.response.ApiResponse;
import com.wou.kyn.payload.response.JwtAuthenticationResponse;
import com.wou.kyn.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	@Autowired
	private AuthService authService;

	/**
	 * User login
	 *
	 * POST /api/auth/login
	 */
	@PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new JwtAuthenticationResponse(authService.authenticateUser(loginRequest)));
    }

	/**
	 * User account registration
	 * 
	 * POST /api/auth/register
	 */
	@PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
    	User user = authService.registerUser(registerRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location)
        					.body(new ApiResponse(true, "User successfully registered"));
    }
}

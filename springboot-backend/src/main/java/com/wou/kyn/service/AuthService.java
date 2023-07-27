package com.wou.kyn.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wou.kyn.entity.Role;
import com.wou.kyn.entity.User;
import com.wou.kyn.exception.AppException;
import com.wou.kyn.exception.BadRequestException;
import com.wou.kyn.payload.request.LoginRequest;
import com.wou.kyn.payload.request.RegisterRequest;
import com.wou.kyn.repository.RoleRepository;
import com.wou.kyn.repository.UserRepository;
import com.wou.kyn.security.JwtTokenProvider;

@Service
@Transactional
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Authenticate user
	 *
	 * @param loginRequest the login request
	 * @return the JWT token
	 */
	public String authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmailOrUsername(),
                        loginRequest.getPassword()
                )
        );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return jwtTokenProvider.generateToken(authentication);
	}
	
	/**
	 * Register new user
	 *
	 * @param registerRequest the register request
	 * @return the user created
	 */
	public User registerUser(RegisterRequest registerRequest) {
		String username = registerRequest.getUsername();
		String email = registerRequest.getEmail();
		String rawPassword = registerRequest.getPassword();

		if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("Duplicate username found");
        }

        if (userRepository.existsByEmail(email)) {
        	throw new BadRequestException("Duplicate email found");
        }

		// Hash the password
		String hashedPassword = passwordEncoder.encode(rawPassword);

		User user = new User(username, email, hashedPassword);

        // Set USER role
        Role userRole = roleRepository.findByRoleName("USER")
                .orElseThrow(() -> new AppException("Role USER failed to set"));

        user.setRoles(Collections.singleton(userRole));

		// Set provider as LOCAL
		user.setProvider("LOCAL");

        return userRepository.save(user);
	}
}

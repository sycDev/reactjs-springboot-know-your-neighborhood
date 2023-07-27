package com.wou.kyn.security.oauth2;

import com.wou.kyn.entity.Role;
import com.wou.kyn.entity.User;
import com.wou.kyn.exception.AppException;
import com.wou.kyn.exception.OAuth2Exception;
import com.wou.kyn.repository.RoleRepository;
import com.wou.kyn.repository.UserRepository;
import com.wou.kyn.security.UserPrincipal;
import com.wou.kyn.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RandomGenerator randomGenerator;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        try {
        	// Get the provider of OAuth
            String registrationId = userRequest.getClientRegistration().getRegistrationId();

            // Get the information from OAuth user
            OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());
            String oAuth2Id = oAuth2UserInfo.getId();
            String oAuth2Name = oAuth2UserInfo.getName();
            String oAuth2Email = oAuth2UserInfo.getEmail();
            String oAuth2ImgUrl = oAuth2UserInfo.getImgUrl();

            // Confirm that the email from OAuth user is provided
            if (oAuth2Email == null) {
                throw new OAuth2Exception("No email found from " + registrationId + " OAuth2 provider");
            }

            User user;
            // Check for duplicate email
            Optional<User> existingUser = userRepository.findByEmail(oAuth2Email);
            if (!existingUser.isPresent()) {
                user = new User(oAuth2Email, oAuth2Name);
                user.setUsername(randomGenerator.generateUsername());
                user.setPassword(null);
                user.setImgUrl(oAuth2ImgUrl);
                user.setProvider(registrationId.toUpperCase());
                user.setProviderId(oAuth2Id);
                // Set USER role
                Role userRole = roleRepository.findByRoleName("USER")
                        .orElseThrow(() -> new AppException("Role USER failed to set"));

                user.setRoles(Collections.singleton(userRole));

                // Create a new user account record in database
                user = userRepository.save(user);
            } else {
            	// Retrieve the existing user from database
                user = existingUser.get();
                // Update the name to make sure it is sync with our database
                user.setName(oAuth2Name);

                // If the same email address had already registered through another provider
                if (!(user.getProvider()).equalsIgnoreCase(registrationId)) {
                    throw new OAuth2Exception("Please login with your existing " + user.getProvider().toLowerCase() + " account!");
                }
            }

            return UserPrincipal.create(user, oAuth2User.getAttributes());
        } catch (AuthenticationException authException) {
            throw authException;
        } catch (Exception e) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }
}

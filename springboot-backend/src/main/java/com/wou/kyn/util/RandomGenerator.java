package com.wou.kyn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import com.wou.kyn.repository.UserRepository;

@Component
public class RandomGenerator {
    @Autowired
    private UserRepository userRepository;

    /**
     * This method is to generate a random unique username for the social login account by default
     */
    public String generateUsername() {
        int suffixLength = 8;
        String baseUsername = "user_";
        String suffix = UUID.randomUUID().toString().substring(0, suffixLength);
        String username = baseUsername + suffix;

        // Check existence of username
        boolean isUnique = !(userRepository.existsByUsername(username));

        // Generate other unique username if it is taken
        while (!isUnique) {
            suffix = UUID.randomUUID().toString().substring(0, suffixLength);
            username = baseUsername + suffix;
            isUnique = !(userRepository.existsByUsername(username));
        }

        return username;
    }
}

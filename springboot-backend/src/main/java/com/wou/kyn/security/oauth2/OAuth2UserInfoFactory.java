package com.wou.kyn.security.oauth2;

import com.wou.kyn.exception.OAuth2Exception;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase("google")) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2Exception(registrationId + " OAuth2 provider not support yet...");
        }
    }
}

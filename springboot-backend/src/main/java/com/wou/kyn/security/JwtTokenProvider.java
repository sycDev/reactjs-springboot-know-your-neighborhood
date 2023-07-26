package com.wou.kyn.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@SuppressWarnings("deprecation")
@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expired_in_ms}")
    private int EXPIRED_DURATION;

	public String generateToken(Authentication auth) {
    	UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
    	
    	Date now = new Date();
    	Date expiryDate = new Date(now.getTime() + EXPIRED_DURATION);
    	return Jwts.builder()
	    			.setSubject(Long.toString(userPrincipal.getId()))
	    			.setIssuedAt(now)
	    			.setExpiration(expiryDate)
	    			.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
	    			.compact();
    }
    
    public Long getUserIdFromJwt(String token) {
    	Claims claims = Jwts.parser()
    						.setSigningKey(JWT_SECRET)
    						.parseClaimsJws(token)
    						.getBody();
    	return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) {
    	try {
    		Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
    		return true;
    	} catch (SignatureException e) {
    		logger.error("Invalid JWT signature");
    	} catch (MalformedJwtException e) {
    		logger.error("Invalid JWT token");
    	} catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }

        return false;
    }
}

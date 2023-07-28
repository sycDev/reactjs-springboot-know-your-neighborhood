package com.wou.kyn.security;

import com.wou.kyn.security.oauth2.CustomOAuth2UserService;
import com.wou.kyn.security.oauth2.CustomUrlAuthFailureHandler;
import com.wou.kyn.security.oauth2.CustomUrlAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true,
	prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private CustomUrlAuthSuccessHandler customUrlAuthSuccessHandler;

	@Autowired
	private CustomUrlAuthFailureHandler customUrlAuthFailureHandler;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
        	.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/favicon.ico", 
							"/**/*.png", 
							"/**/*.jpg", 
		                    "/**/*.css",
		                    "/**/*.js").permitAll()
				.antMatchers("/api/auth/**", "/oauth2/**", "/login").permitAll()
				.antMatchers("/api/users/checkUsernameExistence", 
							"/api/users/checkEmailExistence").permitAll()
				.antMatchers(HttpMethod.POST, "/api/message/send").permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
			.cors()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
			.oauth2Login()
				.userInfoEndpoint()
					.userService(customOAuth2UserService)
					.and()
				.successHandler(customUrlAuthSuccessHandler)
				.failureHandler(customUrlAuthFailureHandler);
		
		// Add custom JWT security filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}

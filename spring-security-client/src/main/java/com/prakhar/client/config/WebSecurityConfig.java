package com.prakhar.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String [] WHITE_LIST_URLS = {"/register","/hello","/verifyRegistration","/login"};

    // to use password encoder we have to add spring security
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf().disable() // Disable CSRF if you don't need it, but be cautious for APIs.
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(WHITE_LIST_URLS).permitAll() // Allow public URLs
                .anyRequest().authenticated() // Any other requests need to be authenticated
            );
        return http.build();
    }

}

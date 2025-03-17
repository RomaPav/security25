package edu.pavliuk.security25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
    @author romat
    @project security25
    @class SecurityConfig
    @version 1.0.0
    @since 17.03.2025 - 21.29
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/api/v1/movies/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/v1/movies/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/v1/movies/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/**").authenticated()
                )
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }
}


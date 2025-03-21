package edu.pavliuk.security25.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/index.html").permitAll()
                        .requestMatchers("/api/v1/movies/admin").hasRole("ADMIN")
                        .requestMatchers("/api/v1/movies/user").hasRole("USER")
                        .requestMatchers("/api/v1/movies/unknown").hasRole("UNKNOWN")
                        .requestMatchers( HttpMethod.POST,"/api/v1/movies/").hasRole("ADMIN")
                        .requestMatchers( HttpMethod.DELETE,"/api/v1/movies/").hasRole("ADMIN")
                        .requestMatchers( HttpMethod.PUT,"/api/v1/movies/").hasRole("ADMIN")
                        .requestMatchers( HttpMethod.GET,"/api/v1/movies/**").hasAnyRole("ADMIN", "USER", "UNKNOWN")
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build();
        UserDetails unknown = User.builder()
                .username("unknown")
                .password(passwordEncoder().encode("unknown"))
                .roles("UNKNOWN").build();
        return new InMemoryUserDetailsManager(admin, user, unknown);
    }
}


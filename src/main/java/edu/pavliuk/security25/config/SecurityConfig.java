package edu.pavliuk.security25.config;

import edu.pavliuk.security25.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
    @author romat
    @project security25
    @class SecurityConfig
    @version 1.0.0
    @since 17.03.2025 - 21.29
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static Advisor preAuthorizeMethodInterceptor(){
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
    }

//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req

                        .anyRequest().permitAll())
//                        .requestMatchers("/index.html", "/api/auth/**").permitAll()
//                        .requestMatchers("/api/v1/movies/admin").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/movies/user").hasRole("USER")
//                        .requestMatchers("/api/v1/movies/unknown").hasRole("UNKNOWN")
//                        .requestMatchers( HttpMethod.POST,"/api/v1/movies/").hasRole("ADMIN")
//                        .requestMatchers( HttpMethod.DELETE,"/api/v1/movies/").hasRole("ADMIN")
//                        .requestMatchers( HttpMethod.PUT,"/api/v1/movies/").hasRole("ADMIN")
//                        .requestMatchers( HttpMethod.GET,"/api/v1/movies/**").hasAnyRole("ADMIN", "USER", "UNKNOWN")
//                        .anyRequest()
//                        .authenticated())
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore()
//                .httpBasic(Customizer.withDefaults())
        ;


        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN").build();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER").build();
//        UserDetails unknown = User.builder()
//                .username("unknown")
//                .password(passwordEncoder().encode("unknown"))
//                .roles("UNKNOWN").build();
//        return new InMemoryUserDetailsManager(admin, user, unknown);
//    }
}


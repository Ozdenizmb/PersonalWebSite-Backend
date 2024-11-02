package com.baranozdeniz.personalwebsite.config;

import com.baranozdeniz.personalwebsite.exception.AuthException;
import com.baranozdeniz.personalwebsite.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final AuthService authService;

    private static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/docs/swagger-ui/**",
            "/swagger-ui.html"
    };

    private static final String[] PERMIT_ALL_ENDPOINTS = {
            "/api/v1/users/signup/user",
            "/api/v1/users/signup/admin",
            "/api/v1/users/login/user/{email}",
            "/api/v1/users/login/admin/{email}",
            "/api/v1/users/get/user",
            "/api/v1/users/get/id/{id}",
            "/api/v1/users/get/email/{email}",
            "/api/v1/users/get/admin",
            "/api/v1/projects/get",
            "/api/v1/projects/get/id/{id}",
            "/api/v1/projects/get/name/{name}",
            "/api/v1/projects/get/count",
            "/api/v1/likes/get/{projectId}",
            "/api/v1/comments/get/{id}",
            "/api/v1/comments/get/project/{projectId}",
            "/api/v1/comments/get/user/{userId}"
    };

    private static final String[] USER_ENDPOINTS = {
            "/api/v1/users/update/user/{id}",
            "/api/v1/users/delete/user/{id}",
            "/api/v1/likes/add",
            "/api/v1/likes/didILikeIt",
            "/api/v1/likes/delete",
            "/api/v1/comments/create",
            "/api/v1/comments/update/{id}",
            "/api/v1/comments/delete/{id}",
            "/api/v1/contacts/create"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/update/admin/{id}",
            "/api/v1/users/delete/admin/{id}",
            "/api/v1/projects/create",
            "/api/v1/projects/update/{id}",
            "/api/v1/projects/delete/{id}",
            "/api/v1/contacts/get/{id}",
            "/api/v1/contacts/getpage",
            "/api/v1/contacts/delete/{id}",
            "/api/v1/assets/upload",
            "/api/v1/assets/delete/{fileName}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry ->{
                    registry.requestMatchers(AUTH_WHITELIST).permitAll();
                    registry.requestMatchers(PERMIT_ALL_ENDPOINTS).permitAll();
                    registry.requestMatchers(USER_ENDPOINTS).hasAnyRole("USER", "ADMIN");
                    registry.requestMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN");
                    registry.anyRequest().authenticated();
                })
                .httpBasic(httpBasicConfigurer -> httpBasicConfigurer
                        .authenticationEntryPoint(unauthorizedEntryPoint())
                )
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer.authenticationEntryPoint(unauthorizedEntryPoint())
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return authService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(authService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return new AuthException();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

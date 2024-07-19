package com.baranozdeniz.personalwebsite.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AuthService extends UserDetailsService {

    Boolean verifyUserIdMatchesAuthenticatedUser(UUID id);

}

package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.model.User;
import com.baranozdeniz.personalwebsite.repository.UserRepository;
import com.baranozdeniz.personalwebsite.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    // Login Process
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> response = userRepository.findByEmail(email);

        if(response.isPresent()) {
            User existUser = response.get();

            if(existUser.getRole().equals("ADMIN")) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(existUser.getEmail())
                        .password(existUser.getPassword())
                        .roles("ADMIN")
                        .build();
            }
            else {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(existUser.getEmail())
                        .password(existUser.getPassword())
                        .roles("USER")
                        .build();
            }

        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.EMAIL_NOT_FOUND);
        }
    }

}

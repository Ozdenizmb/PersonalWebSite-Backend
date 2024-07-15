package com.baranozdeniz.personalwebsite.controller;

import com.baranozdeniz.personalwebsite.api.UserApi;
import com.baranozdeniz.personalwebsite.dto.UserCreateDto;
import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.dto.UserUpdateDto;
import com.baranozdeniz.personalwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<UserDto> signUpUser(UserCreateDto userCreateDto) {
        return ResponseEntity.ok(service.signUpUser(userCreateDto));
    }

    @Override
    public ResponseEntity<UserDto> signUpAdmin(String key, UserCreateDto userCreateDto) {
        return ResponseEntity.ok(service.signUpAdmin(key, userCreateDto));
    }

    @Override
    public ResponseEntity<UserDto> getUserWithEmail(String email) {
        return ResponseEntity.ok(service.getUserWithEmail(email));
    }

    @Override
    public ResponseEntity<UserDto> getUserWithId(UUID id) {
        return ResponseEntity.ok(service.getUserWithId(id));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDto> getAdminWithEmail(String email) {
        return ResponseEntity.ok(service.getAdminWithEmail(email));
    }

    @Override
    public ResponseEntity<UserDto> getAdminWithId(UUID id) {
        return ResponseEntity.ok(service.getAdminWithId(id));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllAdmins() {
        return ResponseEntity.ok(service.getAllAdmins());
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UUID id, UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(service.updateUser(id, userUpdateDto));
    }

    @Override
    public ResponseEntity<UserDto> updateAdmin(String key, UUID id, UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(service.updateAdmin(key, id, userUpdateDto));
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(UUID id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }

    @Override
    public ResponseEntity<Boolean> deleteAdmin(String key, UUID id) {
        return ResponseEntity.ok(service.deleteAdmin(key, id));
    }

}

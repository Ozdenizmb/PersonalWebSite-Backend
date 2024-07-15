package com.baranozdeniz.personalwebsite.service;

import com.baranozdeniz.personalwebsite.dto.UserCreateDto;
import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.dto.UserUpdateDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto signUpUser(UserCreateDto userCreateDto);
    UserDto signUpAdmin(String key, UserCreateDto userCreateDto);
    UserDto getUserWithEmail(String email);
    UserDto getUserWithId(UUID id);
    List<UserDto> getAllUsers();
    UserDto getAdminWithEmail(String email);
    UserDto getAdminWithId(UUID id);
    List<UserDto> getAllAdmins();
    UserDto updateUser(UUID id, UserUpdateDto userUpdateDto);
    UserDto updateAdmin(String key, UUID id, UserUpdateDto userUpdateDto);
    Boolean deleteUser(UUID id);
    Boolean deleteAdmin(String key, UUID id);

}

package com.baranozdeniz.personalwebsite.service;

import com.baranozdeniz.personalwebsite.dto.UserCreateDto;
import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.dto.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserService {

    UserDto signUpUser(UserCreateDto userCreateDto, MultipartFile file);
    UserDto signUpAdmin(String key, UserCreateDto userCreateDto, MultipartFile file);
    UserDto getUserWithEmail(String email);
    UserDto getUserWithId(UUID id);
    Page<UserDto> getAllUsers(Pageable pageable);
    UserDto getAdminWithEmail(String email);
    UserDto getAdminWithId(UUID id);
    Page<UserDto> getAllAdmins(Pageable pageable);
    UserDto updateUser(UUID id, UserUpdateDto userUpdateDto, MultipartFile file);
    UserDto updateAdmin(String key, UUID id, UserUpdateDto userUpdateDto, MultipartFile file);
    Boolean deleteUser(UUID id);
    Boolean deleteAdmin(String key, UUID id);

}

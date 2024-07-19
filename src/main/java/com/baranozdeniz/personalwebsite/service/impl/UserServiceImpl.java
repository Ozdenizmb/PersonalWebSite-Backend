package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.UserCreateDto;
import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.dto.UserUpdateDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.PageMapperHelper;
import com.baranozdeniz.personalwebsite.mapper.UserMapper;
import com.baranozdeniz.personalwebsite.model.User;
import com.baranozdeniz.personalwebsite.repository.UserRepository;
import com.baranozdeniz.personalwebsite.service.AuthService;
import com.baranozdeniz.personalwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final FileServiceImpl fileService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Value("${admin.key}")
    private String adminKey;
    @Value("${file.allowed-formats}")
    private String[] allowedFormats;

    @Override
    public UserDto signUpUser(UserCreateDto userCreateDto, MultipartFile file) {
        User user = new User();
        BeanUtils.copyProperties(userCreateDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(file != null && !file.isEmpty()) {
            user.setImageUrl(fileService.uploadFile(file));
        }
        user.setRole("USER");

        try{
            return mapper.toDto(repository.save(user));
        } catch (Exception e) {
            if(user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                fileService.deleteFile(user.getImageUrl());
            }
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public UserDto signUpAdmin(String key, UserCreateDto userCreateDto, MultipartFile file) {
        if(adminKey.equals(key)) {
            User user = new User();
            BeanUtils.copyProperties(userCreateDto, user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if(file != null && !file.isEmpty()) {
                user.setImageUrl(fileService.uploadFile(file));
            }
            user.setRole("ADMIN");

            try{
                return mapper.toDto(repository.save(user));
            } catch (Exception e) {
                if(user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                    fileService.deleteFile(user.getImageUrl());
                }
                throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

    @Override
    public UserDto loginUser(String email, String password) {
        Optional<User> responseUser = repository.findByEmail(email);

        if(responseUser.isPresent() && !responseUser.get().getRole().equals("ADMIN")) {
            User existUser = responseUser.get();

            if(passwordEncoder.matches(password, existUser.getPassword())) {
                return mapper.toDto(existUser);
            }
            else {
                throw PwsException.withStatusAndMessage(HttpStatus.UNAUTHORIZED, ErrorMessages.INCORRECT_LOGIN);
            }

        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }
    }

    @Override
    public UserDto loginAdmin(String key, String email, String password) {
        if(adminKey.equals(key)) {
            Optional<User> responseUser = repository.findByEmail(email);

            if(responseUser.isPresent() && !responseUser.get().getRole().equals("ADMIN")) {
                User existUser = responseUser.get();

                if(passwordEncoder.matches(password, existUser.getPassword())) {
                    return mapper.toDto(existUser);
                }
                else {
                    throw PwsException.withStatusAndMessage(HttpStatus.UNAUTHORIZED, ErrorMessages.INCORRECT_LOGIN);
                }

            }
            else {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
            }
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

    @Override
    public UserDto getUserWithEmail(String email) {
        Optional<User> responseUser = repository.findByEmail(email);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return mapper.toDto(responseUser.get());
    }

    @Override
    public UserDto getUserWithId(UUID id) {
        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return mapper.toDto(responseUser.get());
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        Page<User> responseUsers = repository.findAllByRole("USER", pageable);

        if(responseUsers.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return PageMapperHelper.mapEntityPageToDtoPage(responseUsers, mapper);
    }

    @Override
    public UserDto getAdminWithEmail(String email) {
        Optional<User> responseUser = repository.findByEmail(email);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
        }

        return mapper.toDto(responseUser.get());
    }

    @Override
    public UserDto getAdminWithId(UUID id) {
        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
        }

        return mapper.toDto(responseUser.get());
    }

    @Override
    public Page<UserDto> getAllAdmins(Pageable pageable) {
        Page<User> responseUsers = repository.findAllByRole("ADMIN", pageable);

        if(responseUsers.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
        }

        return PageMapperHelper.mapEntityPageToDtoPage(responseUsers, mapper);
    }

    @Override
    public UserDto updateUser(UUID id, UserUpdateDto userUpdateDto, MultipartFile file) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(id)) {
            throw PwsException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.UNAUTHORIZED);
        }

        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User existUser = responseUser.get();
        BeanUtils.copyProperties(userUpdateDto, existUser);
        existUser.setPassword(passwordEncoder.encode(existUser.getPassword()));

        if(file != null && !file.isEmpty()) {
            String fileType = Objects.requireNonNull(file.getContentType()).split("/")[1];
            if(!Arrays.asList(allowedFormats).contains(fileType)) {
                throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.UNSUPPORTED_FILE_TYPE);
            }

            String currentImageUrl = existUser.getImageUrl();

            if(currentImageUrl != null && !currentImageUrl.isEmpty()) {
                fileService.deleteFile(currentImageUrl);
            }
            String newFileName = fileService.uploadFile(file);
            existUser.setImageUrl(newFileName);
        }

        return mapper.toDto(repository.save(existUser));
    }

    @Override
    public UserDto updateAdmin(String key, UUID id, UserUpdateDto userUpdateDto, MultipartFile file) {
        if(adminKey.equals(key)) {
            Optional<User> responseUser = repository.findById(id);

            if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
            }

            User existUser = responseUser.get();
            BeanUtils.copyProperties(userUpdateDto, existUser);
            existUser.setPassword(passwordEncoder.encode(existUser.getPassword()));

            if(file != null && !file.isEmpty()) {
                String fileType = Objects.requireNonNull(file.getContentType()).split("/")[1];
                if(!Arrays.asList(allowedFormats).contains(fileType)) {
                    throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.UNSUPPORTED_FILE_TYPE);
                }

                String currentImageUrl = existUser.getImageUrl();

                if(currentImageUrl != null && !currentImageUrl.isEmpty()) {
                    fileService.deleteFile(currentImageUrl);
                }
                String newFileName = fileService.uploadFile(file);
                existUser.setImageUrl(newFileName);
            }

            return mapper.toDto(repository.save(existUser));
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

    @Override
    public Boolean deleteUser(UUID id) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(id)) {
            throw PwsException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.UNAUTHORIZED);
        }

        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User existUser = responseUser.get();
        repository.delete(existUser);
        fileService.deleteFile(existUser.getImageUrl());

        return true;
    }

    @Override
    public Boolean deleteAdmin(String key, UUID id) {
        if(adminKey.equals(key)) {
            Optional<User> responseUser = repository.findById(id);

            if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
            }

            User existUser = responseUser.get();
            repository.delete(existUser);
            fileService.deleteFile(existUser.getImageUrl());

            return true;
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

}

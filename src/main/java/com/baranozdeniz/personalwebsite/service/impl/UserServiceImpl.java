package com.baranozdeniz.personalwebsite.service.impl;

import com.baranozdeniz.personalwebsite.dto.UserCreateDto;
import com.baranozdeniz.personalwebsite.dto.UserDto;
import com.baranozdeniz.personalwebsite.dto.UserUpdateDto;
import com.baranozdeniz.personalwebsite.exception.ErrorMessages;
import com.baranozdeniz.personalwebsite.exception.PwsException;
import com.baranozdeniz.personalwebsite.mapper.UserMapper;
import com.baranozdeniz.personalwebsite.model.User;
import com.baranozdeniz.personalwebsite.repository.UserRepository;
import com.baranozdeniz.personalwebsite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Value("${admin.key}")
    private String adminKey;

    @Override
    public UserDto signUpUser(UserCreateDto userCreateDto) {
        User user = new User();
        BeanUtils.copyProperties(userCreateDto, user);
        user.setRole("USER");

        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserDto signUpAdmin(String key, UserCreateDto userCreateDto) {
        if(adminKey.equals(key)) {
            User user = new User();
            BeanUtils.copyProperties(userCreateDto, user);
            user.setRole("ADMIN");

            return mapper.toDto(repository.save(user));
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
    public List<UserDto> getAllUsers() {
        List<User> responseUsers = repository.findAllByRole("USER");

        if(responseUsers.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return mapper.toDtoList(responseUsers);
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
    public List<UserDto> getAllAdmins() {
        List<User> responseUsers = repository.findAllByRole("ADMIN");

        if(responseUsers.isEmpty()) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
        }

        return mapper.toDtoList(responseUsers);
    }

    @Override
    public UserDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User existUser = responseUser.get();
        BeanUtils.copyProperties(userUpdateDto, existUser);

        return mapper.toDto(repository.save(existUser));
    }

    @Override
    public UserDto updateAdmin(String key, UUID id, UserUpdateDto userUpdateDto) {
        if(adminKey.equals(key)) {
            Optional<User> responseUser = repository.findById(id);

            if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
            }

            User existUser = responseUser.get();
            BeanUtils.copyProperties(userUpdateDto, existUser);

            return mapper.toDto(repository.save(existUser));
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

    @Override
    public Boolean deleteUser(UUID id) {
        Optional<User> responseUser = repository.findById(id);

        if(responseUser.isEmpty() || responseUser.get().getRole().equals("ADMIN")) {
            throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        repository.delete(responseUser.get());

        return true;
    }

    @Override
    public Boolean deleteAdmin(String key, UUID id) {
        if(adminKey.equals(key)) {
            Optional<User> responseUser = repository.findById(id);

            if(responseUser.isEmpty() || responseUser.get().getRole().equals("USER")) {
                throw PwsException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.ADMIN_NOT_FOUND);
            }

            repository.delete(responseUser.get());

            return true;
        }
        else {
            throw PwsException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.WRONG_ADMIN_KEY);
        }
    }

}

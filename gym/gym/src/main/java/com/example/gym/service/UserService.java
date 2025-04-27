package com.example.gym.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gym.dto.request.UserRequest;
import com.example.gym.dto.request.Update.UserUpdateRequest;
import com.example.gym.dto.response.UserResponse;
import com.example.gym.entity.Role;
import com.example.gym.entity.User;
import com.example.gym.enums.RolePlay;
import com.example.gym.exception.AppException;
import com.example.gym.exception.ErrorCode;
import com.example.gym.mapper.UserMapper;
import com.example.gym.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse creatUser(UserRequest userRequest) {
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }
        User user = userMapper.maptoUser(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Role role = new Role();
        role.setId(1L);
        role.setName(RolePlay.USER.name());
        user.setRole(role);
        user.setActive(true);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(Long id, UserUpdateRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        String currentPhoneNumber = user.getPhoneNumber();

        String newPhoneNumber = userRequest.getPhoneNumber();
        if (newPhoneNumber != null && !newPhoneNumber.isEmpty() && !newPhoneNumber.equals(currentPhoneNumber)) {
            if (userRepository.existsByPhoneNumber(newPhoneNumber)) {
                throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
            }
            user.setPhoneNumber(newPhoneNumber);
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_ALREADY_EXISTS);
        }
        userMapper.updateUser(userRequest, user);
        if (userRequest.getPassword() != null && !userRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.INVALID_ID));
        user.setActive(false);
        userRepository.save(user);
    }

}

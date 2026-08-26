package com.qasandbox.backend.service.impl;

import com.qasandbox.backend.dto.user.CreateUserRequest;
import com.qasandbox.backend.cache.UserCache;
import com.qasandbox.backend.client.ExternalProfileClient;
import com.qasandbox.backend.dto.external.ExternalProfileResponse;
import com.qasandbox.backend.dto.user.UpdateUserRequest;
import com.qasandbox.backend.dto.user.UserResponse;
import com.qasandbox.backend.entity.User;
import com.qasandbox.backend.event.UserEventPublisher;
import com.qasandbox.backend.exception.ResourceNotFoundException;
import com.qasandbox.backend.exception.UserAlreadyExistsException;
import com.qasandbox.backend.mapper.UserMapper;
import com.qasandbox.backend.repository.UserRepository;
import com.qasandbox.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserEventPublisher userEventPublisher;
    private final UserCache userCache;
    private final ExternalProfileClient externalProfileClient;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserMapper userMapper,
            UserEventPublisher userEventPublisher,
            UserCache userCache,
            ExternalProfileClient externalProfileClient
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userEventPublisher = userEventPublisher;
        this.userCache = userCache;
        this.externalProfileClient = externalProfileClient;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {

        return userCache.get(id).orElseGet(() -> {
            User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
            UserResponse response = userMapper.toResponse(user);
            userCache.put(response);
            return response;
        });
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException(request.email());
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);
        userEventPublisher.publish("USER_CREATED", savedUser);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        userRepository.findByEmail(request.email())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new UserAlreadyExistsException(request.email());
                });

        userMapper.updateEntity(user, request);

        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.password()));
        }

        User updatedUser = userRepository.save(user);
        userCache.evict(id);
        userEventPublisher.publish("USER_UPDATED", updatedUser);

        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }

        userRepository.deleteById(id);
        userCache.evict(id);
    }

    @Override
    public ExternalProfileResponse getExternalProfile(Long id) {
        return externalProfileClient.getProfile(id);
    }
}

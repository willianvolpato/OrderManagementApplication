package com.example.ordermanagement.service;

import com.example.ordermanagement.model.dto.UserDto;
import com.example.ordermanagement.model.entity.User;
import com.example.ordermanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService (
        final UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User createUser(final UserDto userDto) {
        User userEntity = User.builder()
            .name(userDto.getName())
            .email(userDto.getEmail())
            .build();

        return userRepository.save(userEntity);
    }

    public Optional<User> updateUser(final Long userId, final UserDto userDto) {
        return this.getUserById(userId)
            .map(userEntity -> {
                userEntity.setName(userDto.getName());
                userEntity.setEmail(userDto.getEmail());
                return userRepository.saveAndFlush(userEntity);
            });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteUser(final Long userId) {
        Optional<User> user = this.getUserById(userId);

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }

        return false;
    }

    public User getOrCreateUser(final UserDto userDto) {
        if(ObjectUtils.isEmpty(userDto))
            return null;

        if(ObjectUtils.isEmpty(userDto.getId()))
            return this.createUser(userDto);

        return this.getUserById(userDto.getId())
            .orElseGet(() -> this.createUser(userDto));
    }
}

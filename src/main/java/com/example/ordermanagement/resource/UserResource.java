package com.example.ordermanagement.resource;

import com.example.ordermanagement.model.dto.UserDto;
import com.example.ordermanagement.model.entity.User;
import com.example.ordermanagement.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(
        final UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
        @RequestBody
        final UserDto userDto
    ) {
        User user = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(
        @PathVariable
        final Long userId,
        @RequestBody
        final UserDto userDto
    ) {
        return userService.updateUser(userId, userDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(
        @PathVariable
        final Long userId
    ) {
        return userService.getUserById(userId)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable
        final Long userId
    ) {
        return userService.deleteUser(userId)
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}

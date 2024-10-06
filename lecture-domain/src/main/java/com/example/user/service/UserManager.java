package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.JoinRequest;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository userRepository;

    public User join(JoinRequest joinRequest) {
        checkEmail(joinRequest.email());
        User user = new User(
                joinRequest.username(),
                joinRequest.email(),
                joinRequest.password()
        );
        return userRepository.save(user);
    }

    private void checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .stream()
                .filter(it -> it.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }

}

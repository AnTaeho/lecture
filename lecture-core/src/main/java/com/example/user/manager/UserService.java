package com.example.user.manager;

import com.example.user.controller.dto.LoginRequest;
import com.example.user.controller.dto.UserResponse;
import com.example.user.dto.JoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserManager userManager;

    public UserResponse join(JoinRequest joinRequest) {
        return new UserResponse(userManager.join(joinRequest).getId());
    }

    public UserResponse login(LoginRequest loginRequest) {
        return new UserResponse(userManager.login(loginRequest.email(), loginRequest.password()).getId());
    }

}

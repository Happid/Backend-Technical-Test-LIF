package com.lif.controller;

import com.lif.model.dto.ApiResponse;
import com.lif.model.dto.LoginRequest;
import com.lif.model.dto.LoginResponse;
import com.lif.model.dto.RegisterRequest;
import com.lif.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        LoginResponse response = authService.login(request);
        return ApiResponse.success("Login successful", response);
    }

    @PostMapping("/register")
    public ApiResponse<?> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        authService.register(request);
        return ApiResponse.success("User registered successfully", null);
    }
}

package com.choishingong.s_timer.auth.controller;

import com.choishingong.s_timer.auth.dto.LoginRequest;
import com.choishingong.s_timer.auth.dto.LoginResponse;
import com.choishingong.s_timer.auth.dto.SignupRequest;
import com.choishingong.s_timer.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    // 로그인/회원가입 api
    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}

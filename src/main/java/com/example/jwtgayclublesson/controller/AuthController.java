package com.example.jwtgayclublesson.controller;

import com.example.jwtgayclublesson.auth.AuthOrRegisterService;
import com.example.jwtgayclublesson.dto.AuthOrRegisterResponseDto;
import com.example.jwtgayclublesson.dto.AuthRequestDto;
import com.example.jwtgayclublesson.dto.RegisterRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthOrRegisterService authOrRegisterService;

    @PostMapping("/register")
    public AuthOrRegisterResponseDto register(@RequestBody RegisterRequestDto registerRequestDto) {
        return authOrRegisterService.register(registerRequestDto);
    }

    @PostMapping("/authenticate")
    public AuthOrRegisterResponseDto authenticate(@RequestBody AuthRequestDto authRequestDto) {
        return authOrRegisterService.authenticate(authRequestDto);
    }
}

package com.oficina.api.controller;

import com.oficina.application.dto.LoginRequestDTO;
import com.oficina.application.dto.LoginResponseDTO;
import com.oficina.domain.exception.BusinessException;
import com.oficina.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;

    @Value("${security.admin.username:admin}")
    private String adminUsername;

    @Value("${security.admin.password:admin123}")
    private String adminPassword;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        if (request == null
                || !adminUsername.equals(request.getUsername())
                || !adminPassword.equals(request.getPassword())) {
            throw new BusinessException("Credenciais administrativas inválidas.");
        }
        return new LoginResponseDTO(jwtUtils.generateToken(request.getUsername()), "Bearer");
    }
}

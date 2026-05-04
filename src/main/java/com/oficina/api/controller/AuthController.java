package com.oficina.api.controller;

import com.oficina.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        // No MVP, aceitamos qualquer usuário e geramos um token
        return jwtUtils.generateToken(username);
    }
}

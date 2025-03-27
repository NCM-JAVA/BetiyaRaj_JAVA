package com.bor.rcms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bor.rcms.dto.AuthRequest;
import com.bor.rcms.dto.AuthResponse;
import com.bor.rcms.dto.LoginRequest;
import com.bor.rcms.dto.OtpLoginRequest;
import com.bor.rcms.resonse.LoginResponse;
import com.bor.rcms.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = userService.loginAndGenerateToken(request.getUserName(), request.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/login-officer")
    public ResponseEntity<?> loginOfficer(@RequestBody LoginRequest request) {
    	LoginResponse   Stringeres  = userService.loginOfficer(request.getUserName(), request.getPassword(),request.getUserType());
        return ResponseEntity.ok(Stringeres);
    }

    @PostMapping("/login-objectioner")
    public ResponseEntity<?> loginObjectioner(@RequestBody OtpLoginRequest request) {
        String token = userService.loginObjectioner(request.getMobileNumber(), request.getOtp());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

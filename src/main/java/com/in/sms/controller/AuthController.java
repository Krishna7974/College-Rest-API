package com.in.sms.controller;

import com.in.sms.dto.auth.LoginRequestDto;
import com.in.sms.jwt.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login Controller")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/student")
    @Operation(summary = "Login for Student")
    public ResponseEntity<String> loginStudent(@RequestBody LoginRequestDto loginRequestDto) {
        logger.info("Student login attempt: email={}", loginRequestDto.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
            logger.info("Student login successful: email={}", loginRequestDto.getEmail());
            return ResponseEntity.ok(jwtToken);
        } catch (BadCredentialsException e) {
            logger.warn("Student login failed due to invalid credentials: email={}", loginRequestDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/teacher")
    @Operation(summary = "Login for Teacher")
    public ResponseEntity<String> loginTeacher(@RequestBody LoginRequestDto loginRequestDto) {
        logger.info("Teacher login attempt: email={}", loginRequestDto.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getEmail(),
                            loginRequestDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
            logger.info("Teacher login successful: email={}", loginRequestDto.getEmail());
            return ResponseEntity.ok(jwtToken);
        } catch (BadCredentialsException e) {
            logger.warn("Teacher login failed due to invalid credentials: email={}", loginRequestDto.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}

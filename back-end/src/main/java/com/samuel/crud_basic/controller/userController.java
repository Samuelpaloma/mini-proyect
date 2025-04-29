package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class userController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO userDTO) {
        responseDTO response = userService.registerUser(userDTO);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.badRequest().body(response.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody UserDTO userDTO) {
        responseDTO response = userService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.getMessage());
        }
    }
}
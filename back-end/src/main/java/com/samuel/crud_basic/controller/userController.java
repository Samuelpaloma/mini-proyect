package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class userController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO user) {
        userService.save(user);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }

}
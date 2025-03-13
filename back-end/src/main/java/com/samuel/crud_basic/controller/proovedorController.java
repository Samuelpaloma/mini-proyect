package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.ProovedorDTO;
import com.samuel.crud_basic.service.ProovedorService;

@RestController
@RequestMapping("/api/v1/proovedor")

public class proovedorController {

    @Autowired
    private ProovedorService proovedorService;

    @PostMapping("/")
    public ResponseEntity<Object> registerProovedor(@RequestBody ProovedorDTO proovedor){
        proovedorService.save(proovedor);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}

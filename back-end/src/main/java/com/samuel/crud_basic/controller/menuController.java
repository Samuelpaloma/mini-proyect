package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.MenuDTO;
import com.samuel.crud_basic.service.MenuService;

@RestController
@RequestMapping("/api/v1/menu")

public class menuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMenu(@RequestBody MenuDTO menu){
        menuService.save(menu);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}

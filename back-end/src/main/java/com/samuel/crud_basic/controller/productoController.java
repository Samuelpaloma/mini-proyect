package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.ProductoDTO;
import com.samuel.crud_basic.service.ProductoService;

@RestController
@RequestMapping("/api/v1/producto")

public class productoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/")
    public ResponseEntity<Object> registerProducto(@RequestBody ProductoDTO producto){
        productoService.save(producto);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}

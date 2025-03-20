package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.service.MesaService;

@RestController
@RequestMapping("/api/v1/mesa")

public class mesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMesa(@RequestBody MesaDTO mesa){
        mesaService.save(mesa);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}

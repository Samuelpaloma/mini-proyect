package com.samuel.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.service.MesaService;

@RestController
@RequestMapping("/api/v1/mesa")

public class mesaController {

    
    @Autowired
    private MesaService mesaService;

    @GetMapping("/disponibles")
    public ResponseEntity<List<Mesa>> getMesasDisponibles() {
        List<Mesa> mesasDisponibles = mesaService.getMesasDisponibles();
        return ResponseEntity.ok(mesasDisponibles);
    }

    @PutMapping("/liberar/{id}")
    public ResponseEntity<Object> liberarMesa(@PathVariable int id) {
        boolean result = mesaService.liberarMesa(id);
        if (result) {
            return ResponseEntity.ok("Mesa liberada con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mesa no encontrada");
        }
    }
    
}

package com.samuel.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.ProovedorDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Proovedor;
import com.samuel.crud_basic.service.ProovedorService;

@RestController
@RequestMapping("/api/v1/proovedor")
public class proovedorController {

    @Autowired
    private ProovedorService proovedorService;

    // Obtener todos los proveedores
    @GetMapping("/")
    public ResponseEntity<List<Proovedor>> getAllProovedores() {
        List<Proovedor> proovedores = proovedorService.findAll();
        return ResponseEntity.ok(proovedores);
    }

    // Agregar un nuevo proveedor
    @PostMapping("/")
    public ResponseEntity<Object> addProovedor(@RequestBody ProovedorDTO proovedorDTO) {
        responseDTO response = proovedorService.addProovedor(proovedorDTO);
        return ResponseEntity.ok(response.getMessage());
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProovedor(@PathVariable int id) {
        responseDTO response = proovedorService.deleteProovedor(id);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }
}
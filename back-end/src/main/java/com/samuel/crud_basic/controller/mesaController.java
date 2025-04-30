package com.samuel.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.service.MesaService;

@RestController
@RequestMapping("/api/v1/mesa")
public class mesaController {

    @Autowired
    private MesaService mesaService;

    // Obtener todas las mesas
    @GetMapping("/")
    public ResponseEntity<List<Mesa>> getAllMesas() {
        List<Mesa> mesas = mesaService.findAll();
        return ResponseEntity.ok(mesas);
    }

    // Obtener mesas disponibles
    @GetMapping("/available")
    public ResponseEntity<List<Mesa>> getAvailableMesas() {
        List<Mesa> mesas = mesaService.findAvailableMesas();
        return ResponseEntity.ok(mesas);
    }

    // Agregar una nueva mesa
    @PostMapping("/")
    public ResponseEntity<Object> addMesa(@RequestBody MesaDTO mesaDTO) {
        responseDTO response = mesaService.addMesa(mesaDTO);
        return ResponseEntity.ok(response.getMessage());
    }

    // Actualizar una mesa existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMesa(@PathVariable int id, @RequestBody MesaDTO mesaDTO) {
        responseDTO response = mesaService.updateMesa(id, mesaDTO);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }

    // Eliminar una mesa por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMesa(@PathVariable int id) {
        responseDTO response = mesaService.deleteMesa(id);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }
}
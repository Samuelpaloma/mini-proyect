package com.samuel.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.ProductoDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Producto;
import com.samuel.crud_basic.service.ProductoService;

@RestController
@RequestMapping("/api/v1/producto")
public class productoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping("/")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    // Agregar un nuevo producto
    @PostMapping("/")
    public ResponseEntity<Object> addProducto(@RequestBody ProductoDTO productoDTO) {
        responseDTO response = productoService.addProducto(productoDTO);
        return ResponseEntity.ok(response.getMessage());
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProducto(@PathVariable int id, @RequestBody ProductoDTO productoDTO) {
        responseDTO response = productoService.updateProducto(id, productoDTO);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProducto(@PathVariable int id) {
        responseDTO response = productoService.deleteProducto(id);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }
}
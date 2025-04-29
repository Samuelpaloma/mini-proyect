package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.ProductoDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Producto;
import com.samuel.crud_basic.repository.Iproducto;

@Service
public class ProductoService {

    @Autowired
    private Iproducto productoRepository;

    // Obtener todos los productos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Agregar un nuevo producto
    public responseDTO addProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto(
            0, // ID autogenerado
            productoDTO.getNombre(),
            productoDTO.getPrecio()
        );

        productoRepository.save(producto);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Producto agregado correctamente"
        );
    }

    // Actualizar un producto existente
    public responseDTO updateProducto(int id, ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if (!productoOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El producto con ID " + id + " no existe"
            );
        }

        Producto producto = productoOptional.get();
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());

        productoRepository.save(producto);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Producto actualizado correctamente"
        );
    }

    // Eliminar un producto por ID
    public responseDTO deleteProducto(int id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);

        if (!productoOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El producto con ID " + id + " no existe"
            );
        }

        productoRepository.deleteById(id);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Producto eliminado correctamente"
        );
    }
}
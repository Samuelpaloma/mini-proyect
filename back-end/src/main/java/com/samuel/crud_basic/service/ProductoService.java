package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.ProductoDTO;
import com.samuel.crud_basic.model.Producto;
import com.samuel.crud_basic.repository.Iproducto;



@Service
public class ProductoService {


    @Autowired
    private Iproducto data;

    public void save(ProductoDTO productoDTO) {
        Producto productoRegister = convertToModel(productoDTO);
        data.save(productoRegister);
    }
        
    public ProductoDTO convertToDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO(
            producto.getNombre(),
            producto.getPrecio()
        );
        return productoDTO;
    }
    
    public Producto convertToModel(ProductoDTO productoDTO) {
        Producto producto = new Producto(
            0,
            productoDTO.getNombre(),
            productoDTO.getPrecio()
        );
        return producto;
    }
}


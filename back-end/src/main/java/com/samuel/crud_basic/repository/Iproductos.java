package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Producto;

public interface  Iproductos extends JpaRepository
<Producto, Integer> 

{
    
}
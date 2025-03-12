package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Factura;


public interface  Ifactura extends JpaRepository
<Factura, Integer> 

{
    
}

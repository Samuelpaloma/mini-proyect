package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Proovedor;

public interface  Iproovedor extends JpaRepository
<Proovedor, Integer> 

{
    
}

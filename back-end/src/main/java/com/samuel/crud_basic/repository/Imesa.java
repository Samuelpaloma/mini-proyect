package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Mesa;

public interface  Imesa extends JpaRepository
<Mesa, Integer> 

{
    
}
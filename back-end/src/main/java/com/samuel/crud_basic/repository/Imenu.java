package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Menu;

public interface  Imenu extends JpaRepository
<Menu, Integer> 

{
    
}

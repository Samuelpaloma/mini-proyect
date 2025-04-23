package com.samuel.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.crud_basic.model.Menu;

public interface  Imenu extends JpaRepository
<Menu, Integer> 

{
    @Query("SELECT u FROM Menu u WHERE u.status != false")
    List<Menu> getListMenuActive();

    @Query("SELECT u FROM Menu u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :filter, '%'))")
    List<Menu> getListMenuForName(String filter);
}

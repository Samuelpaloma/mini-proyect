package com.samuel.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuel.crud_basic.model.Employee;

public interface  Iemployee extends JpaRepository
<Employee, Integer> 

{
    
}
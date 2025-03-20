package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.EmployeeDTO;
import com.samuel.crud_basic.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")

public class employeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<Object> registerEmployee(@RequestBody EmployeeDTO employee){
        employeeService.save(employee);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}

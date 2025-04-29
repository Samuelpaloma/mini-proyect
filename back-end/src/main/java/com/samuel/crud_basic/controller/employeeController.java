package com.samuel.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.EmployeeDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Employee;
import com.samuel.crud_basic.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class employeeController {

    @Autowired
    private EmployeeService employeeService;

    // Obtener todos los empleados
    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    // Agregar un nuevo empleado
    @PostMapping("/")
    public ResponseEntity<Object> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        responseDTO response = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(response.getMessage());
    }

    // Actualizar un empleado existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable int id, @RequestBody EmployeeDTO employeeDTO) {
        responseDTO response = employeeService.updateEmployee(id, employeeDTO);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }

    // Eliminar un empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
        responseDTO response = employeeService.deleteEmployee(id);
        if (response.getStatus().equals("200 OK")) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(404).body(response.getMessage());
        }
    }
}
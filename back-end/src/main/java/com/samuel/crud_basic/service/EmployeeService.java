package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.EmployeeDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Employee;
import com.samuel.crud_basic.repository.Iemployee;

@Service
public class EmployeeService {

    @Autowired
    private Iemployee employeeRepository;

    // Obtener todos los empleados
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Agregar un nuevo empleado
    public responseDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
            0, // ID autogenerado
            employeeDTO.getName(),
            employeeDTO.getCargo(),
            employeeDTO.getTelefono()
        );

        employeeRepository.save(employee);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Empleado agregado correctamente"
        );
    }

    // Actualizar un empleado existente
    public responseDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (!employeeOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El empleado con ID " + id + " no existe"
            );
        }

        Employee employee = employeeOptional.get();
        employee.setName(employeeDTO.getName());
        employee.setCargo(employeeDTO.getCargo());
        employee.setTelefono(employeeDTO.getTelefono());

        employeeRepository.save(employee);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Empleado actualizado correctamente"
        );
    }

    // Eliminar un empleado por ID
    public responseDTO deleteEmployee(int id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (!employeeOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El empleado con ID " + id + " no existe"
            );
        }

        employeeRepository.deleteById(id);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Empleado eliminado correctamente"
        );
    }
}
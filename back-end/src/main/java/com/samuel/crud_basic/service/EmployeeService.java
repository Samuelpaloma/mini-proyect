package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.EmployeeDTO;
import com.samuel.crud_basic.model.Employee;
import com.samuel.crud_basic.repository.Iemployee;

@Service
public class EmployeeService {
    

    @Autowired
    private Iemployee data;


    public void save(EmployeeDTO employeeDTO){
        Employee employeeRegister = convertToModel(employeeDTO);
        data.save(employeeRegister);
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO(
            employee.getName(),
            employee.getCargo(),
            employee.getSalario(),
            employee.getTelefono()
        );
        return employeeDTO;
    }
    
    public Employee convertToModel(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(
            0,
            employeeDTO.getName(),
            employeeDTO.getCargo(),
            employeeDTO.getSalario(),
            employeeDTO.getTelefono()
        );
        return employee;
    }
}

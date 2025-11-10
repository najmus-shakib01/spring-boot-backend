package com.shakib.FirstSpringBoot.service;

import com.shakib.FirstSpringBoot.entity.Employee;
import com.shakib.FirstSpringBoot.Repositorie.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // CREATE
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    // READ ALL
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    // READ BY ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    // UPDATE
    public Optional<Employee> updateEmployee(Long id, Employee newData) {
        return employeeRepo.findById(id).map(emp -> {
            emp.setName(newData.getName());
            emp.setAddress(newData.getAddress());
            return employeeRepo.save(emp);
        });
    }

    // DELETE
    public boolean deleteEmployee(Long id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
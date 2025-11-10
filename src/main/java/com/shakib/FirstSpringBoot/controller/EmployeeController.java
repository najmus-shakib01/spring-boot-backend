package com.shakib.FirstSpringBoot.controller;

import com.shakib.FirstSpringBoot.entity.Employee;
import com.shakib.FirstSpringBoot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Root (/) should show endpoints — এজন্য আপনের অ্যাপ চালু হলে / রুটে 404 না পেয়ে এই রিসোর্স দেখতে
    @GetMapping(path = "/", produces = "text/plain")
    public String rootIndex() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available endpoints:\n\n");
        sb.append("POST Method    : api/employees/create\n");
        sb.append("GET Method     : api/employees/all\n");
        sb.append("GET By Specific Id : api/employees/all/{id}\n");
        sb.append("PUT Method     : api/employees/edit/{id}\n");
        sb.append("DELETE Method  : api/employees/delete/{id}\n");
        return sb.toString();
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> list = employeeService.getAllEmployees();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/all/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> opt = employeeService.getEmployeeById(id);
        if (opt.isPresent()) {
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Optional<Employee> updated = employeeService.updateEmployee(id, employee);
        if (updated.isPresent()) {
            return new ResponseEntity<>(updated.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<>("Employee deleted with id: " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
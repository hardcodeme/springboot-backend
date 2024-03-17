package com.example.springbootbackend.controller;

import com.example.springbootbackend.execption.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.Employee_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
    @Autowired
    private Employee_Repository employeeRepository;

//get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    @PostMapping("/add_employees")
    public Employee CreateEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> GetEmployeeById(@PathVariable Long id){
    Employee employee = employeeRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Employee not found for id :"+id));
    return ResponseEntity.ok(employee);
    }
//update employee by id
    @PostMapping("/employees/{id}")
    public ResponseEntity<Employee> UpdateEmployeeById(@PathVariable Long id,@RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Employee not found for id :"+id));
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


}

package com.hcl.demo.controller;

import com.hcl.demo.entity.Employee;
import com.hcl.demo.repository.EmployeeRepository;
import com.hcl.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/")
public class EmployeeController {

    @Autowired
    EmployeeService service;
    @PostMapping("save")
    public Employee saveEmployee(@RequestBody Employee employee){
        return service.saveEmployee(employee);
    }

    @GetMapping("/employee")
    public List<Employee> getEmployee(){
       return service.getEmployee();
    }
}

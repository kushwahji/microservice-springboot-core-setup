package com.hcl.demo.service;

import com.hcl.demo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getEmployee();
}

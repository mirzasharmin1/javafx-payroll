package com.programming2.payroll.services;

import com.programming2.payroll.models.Employee;

import java.util.List;

public class EmployeeService extends BaseService {

    public List<Employee> getAllEmployees() {
        return getAll(Employee.class);
    }

    public Employee getEmployee(int employeeId) {
        return getById(Employee.class, employeeId);
    }
}

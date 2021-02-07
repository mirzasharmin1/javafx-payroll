package com.programming2.payroll.services;

import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService extends BaseService {

    public List<Employee> getAllEmployees() {
        String hql = "FROM Employee ORDER BY firstName, lastName";
        return getRecords(Employee.class, hql, new HashMap<>());
    }

    public Employee getEmployee(int employeeId) {
        return getById(Employee.class, employeeId);
    }

    public List<Employee> getEmployeesForDepartment(int departmentId) {
        String hql = "FROM Employee WHERE departmentId = :departmentId ORDER BY firstName, lastName";

        Map<String, Object> params = new HashMap<>();
        params.put("departmentId", departmentId);

        return getRecords(Employee.class, hql, params);
    }

    public List<Employee> getEmployeesByName(String name) {
        String hql = "FROM Employee WHERE firstName LIKE :name OR lastName LIKE :name ORDER BY firstName, lastName";

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);

        return getRecords(Employee.class, hql, params);
    }

    public boolean deleteEmployee(int employeeId) {
        return deleteById(Employee.class, employeeId);
    }
}

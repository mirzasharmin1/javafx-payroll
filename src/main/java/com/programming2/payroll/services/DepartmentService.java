package com.programming2.payroll.services;

import com.programming2.payroll.models.Department;
import com.programming2.payroll.models.Employee;

import java.util.HashMap;
import java.util.List;

public class DepartmentService extends BaseService {

    public List<Department> getAllDepartments() {
        String hql = "FROM Department ORDER BY name";
        return getRecords(Department.class, hql, new HashMap<>());
    }
}

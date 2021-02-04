package com.programming2.payroll.services;

import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import com.programming2.payroll.models.Payroll;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveService extends BaseService {

    public List<Leave> getLeavesForEmployee(int employeeId) {
        String hql = "FROM Leave WHERE Leave.employeeId = :employeeId";

        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);

        return getRecords(Leave.class, hql, params);
    }

    public Leave getLeave(int leaveId) {
        return getById(Leave.class, leaveId);
    }
}
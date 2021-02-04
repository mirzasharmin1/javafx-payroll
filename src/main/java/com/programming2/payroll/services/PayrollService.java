package com.programming2.payroll.services;

import com.programming2.payroll.models.Leave;
import com.programming2.payroll.models.Payroll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollService extends BaseService {

    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        String hql = "FROM Payroll WHERE employeeId = :employeeId";

        Map<String, Object> params = new HashMap<>();
        params.put("employeeId", employeeId);

        return getRecords(Payroll.class, hql, params);
    }

    public Payroll getPayroll(int payrollId) {
        return getById(Payroll.class, payrollId);
    }
}

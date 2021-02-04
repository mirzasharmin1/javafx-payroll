package com.programming2.payroll.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    private int id;

    @Column(name = "payDate", nullable = false)
    private Date payDate;

    @Column(name = "payForMonth", nullable = false)
    private Integer payForMonth;

    @Column(name = "payForYear", nullable = false)
    private Integer payForYear;

    @Column(name = "payment", nullable = false)
    private Double payment;

    @Column(name = "deduction")
    private Double deduction;

    @Column(name = "deductionNotes")
    private Double deductionNotes;

    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;

    public int getId() {
        return id;
    }

    public Date getPayDate() {
        return payDate;
    }

    public Integer getPayForMonth() {
        return payForMonth;
    }

    public Integer getPayForYear() {
        return payForYear;
    }

    public Double getPayment() {
        return payment;
    }

    public Double getDeduction() {
        return deduction;
    }

    public Double getDeductionNotes() {
        return deductionNotes;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public void setPayForMonth(Integer payForMonth) {
        this.payForMonth = payForMonth;
    }

    public void setPayForYear(Integer payForYear) {
        this.payForYear = payForYear;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public void setDeduction(Double deduction) {
        this.deduction = deduction;
    }

    public void setDeductionNotes(Double deductionNotes) {
        this.deductionNotes = deductionNotes;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

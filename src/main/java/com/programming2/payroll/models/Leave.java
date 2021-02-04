package com.programming2.payroll.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "leave")
public class Leave extends BaseModel {

    @Id
    private int id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "leaveDays", nullable = false)
    private Integer leaveDays;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "employeeId", insertable = false, updatable = false)
    private Employee employee;

    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getLeaveDays() {
        return leaveDays;
    }

    public String getNote() {
        return note;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setLeaveDays(Integer leaveDays) {
        this.leaveDays = leaveDays;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

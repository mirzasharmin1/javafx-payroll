package com.programming2.payroll.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends BaseModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNo", nullable = false)
    private String phoneNo;

    @OneToOne(mappedBy = "employee")
    private Address address;

    @Column(name = "dateOfJoining")
    private Date dateOfJoining;

    @Column(name = "monthlySalary", nullable = false)
    private Double monthlySalary;

    @Column(name = "leaveBalance", nullable = false)
    private Integer leaveBalance;

    @Column(name = "position", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "managerId")
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> managedEmployees;

    @OneToMany(mappedBy = "employee")
    private List<Payroll> payrolls;

    @OneToMany(mappedBy = "employee")
    private List<Leave> leaves;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Address getAddress() {
        return address;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public Integer getLeaveBalance() {
        return leaveBalance;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public Employee getManager() {
        return manager;
    }

    public List<Employee> getManagedEmployees() {
        return managedEmployees;
    }

    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public void setLeaveBalance(Integer leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setManagedEmployees(List<Employee> managedEmployees) {
        this.managedEmployees = managedEmployees;
    }

    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return firstName.concat(" " + lastName);
    }
}

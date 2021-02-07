package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import com.programming2.payroll.services.EmployeeService;
import com.programming2.payroll.services.LeaveService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LeaveViewWindowController extends BaseController implements Initializable {

    private Main main;
    private Leave selectedLeave;
    private Employee selectedEmployee;

    private LeaveService leaveService;
    private EmployeeService employeeService;

    @FXML Label startDateLabel;
    @FXML Label endDateLabel;
    @FXML Label leaveDaysLabel;
    @FXML Label notesLabel;

    public LeaveViewWindowController() {
        leaveService = new LeaveService();
        employeeService = new EmployeeService();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedLeave(Leave selectedLeave) {
        this.selectedLeave = selectedLeave;
        setLabelValues();
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void setLabelValues() {
        if (selectedLeave.getStartDate() != null) {
            String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedLeave.getStartDate());
            startDateLabel.setText(startDateStr);
        }

        if (selectedLeave.getEndDate() != null) {
            String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedLeave.getEndDate());
            endDateLabel.setText(endDateStr);
        }

        if (selectedLeave.getLeaveDays() != null) {
            leaveDaysLabel.setText(selectedLeave.getLeaveDays().toString());
        }

        notesLabel.setText(selectedLeave.getNote());
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.leavesWindow(selectedEmployee);
        }
    }

    public void deleteLeave() {
        if (selectedLeave != null) {

            int leaveBalance = selectedEmployee.getLeaveBalance();
            selectedEmployee.setLeaveBalance(leaveBalance + selectedLeave.getLeaveDays());
            employeeService.createOrUpdateModel(selectedEmployee);

            boolean result = leaveService.deleteLeave(selectedLeave.getId());
            if (result) {
                main.leavesWindow(selectedEmployee);
            }
        }
    }
}

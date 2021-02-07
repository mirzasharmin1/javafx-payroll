package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaveViewWindowController extends BaseController implements Initializable {

    private Main main;
    private Leave setSelectedLeave;
    private Employee selectedEmployee;

    @FXML Label startDateLabel;
    @FXML Label endDateLabel;
    @FXML Label leaveDaysLabel;
    @FXML Label notesLabel;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedLeave(Leave selectedLeave) {
        this.setSelectedLeave = selectedLeave;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.leavesWindow(selectedEmployee);
        }
    }

    public void deleteLeave() {

    }
}

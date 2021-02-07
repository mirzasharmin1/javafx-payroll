package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.common.Utils;
import com.programming2.payroll.models.Employee;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaveFormWindowController  extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    @FXML DatePicker startDateDatePicker;
    @FXML DatePicker endDateDatePicker;
    @FXML TextField leaveDaysTextField;
    @FXML TextField notesTextField;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utils.setNumericFieldValidators(leaveDaysTextField);
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.leavesWindow(selectedEmployee);
        }
    }

    public void saveLeave() {

    }
}

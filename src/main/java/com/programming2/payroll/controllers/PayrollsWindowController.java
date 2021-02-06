package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Employee;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PayrollsWindowController extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

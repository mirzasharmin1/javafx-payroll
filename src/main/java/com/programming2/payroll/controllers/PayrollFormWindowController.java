package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.common.Utils;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import com.programming2.payroll.models.Payroll;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PayrollFormWindowController extends BaseController implements Initializable {

    private Main main;
    private Payroll selectedPayroll;
    private Employee selectedEmployee;

    @FXML DatePicker payDateDatePicker;
    @FXML TextField payMonthTextInput;
    @FXML TextField payYearTextInput;
    @FXML TextField amountTextInput;
    @FXML TextField deductionTextInput;
    @FXML TextField deductionNotesTextInput;

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedPayroll(Payroll selectedPayroll) {
        this.selectedPayroll = selectedPayroll;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utils.setNumericFieldValidators(payMonthTextInput);
        Utils.setNumericFieldValidators(payYearTextInput);
        Utils.setNumericFieldValidators(amountTextInput);
        Utils.setNumericFieldValidators(deductionNotesTextInput);
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.payrollsWindow(selectedEmployee);
        }
    }

    public void savePayroll() {

    }

    public void deletePayroll() {

    }
}

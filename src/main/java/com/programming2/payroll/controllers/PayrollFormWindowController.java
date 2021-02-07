package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.common.Utils;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Payroll;
import com.programming2.payroll.services.PayrollService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class PayrollFormWindowController extends BaseController implements Initializable {

    private Main main;
    private Payroll selectedPayroll;
    private Employee selectedEmployee;

    private PayrollService payrollService;

    @FXML DatePicker payDateDatePicker;
    @FXML TextField payMonthTextInput;
    @FXML TextField payYearTextInput;
    @FXML TextField amountTextInput;
    @FXML TextField deductionTextInput;
    @FXML TextField deductionNotesTextInput;
    @FXML Label errorLabel;
    @FXML Button deleteButton;

    public PayrollFormWindowController() {
        payrollService = new PayrollService();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedPayroll(Payroll selectedPayroll) {
        this.selectedPayroll = selectedPayroll;
        setInputValues();

        deleteButton.setVisible(true);
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Utils.setNumericFieldValidators(payMonthTextInput);
        Utils.setNumericFieldValidators(payYearTextInput);
        Utils.setNumericFieldValidators(amountTextInput);
        Utils.setNumericFieldValidators(deductionTextInput);

        registerFieldsClearErrorOnType();
        deleteButton.setVisible(false);
    }

    private void setInputValues() {
        if (selectedPayroll == null) {
            return;
        }

        if (selectedPayroll.getPayDate() != null) {
            payDateDatePicker.setValue(
                    selectedPayroll.getPayDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );
        }

        if (selectedPayroll.getPayForMonth() != null) {
            payMonthTextInput.setText(selectedPayroll.getPayForMonth().toString());
        }

        if (selectedPayroll.getPayForYear() != null) {
            payYearTextInput.setText(selectedPayroll.getPayForYear().toString());
        }

        if (selectedPayroll.getPayment() != null) {
            amountTextInput.setText(selectedPayroll.getPayment().toString());
        }

        if (selectedPayroll.getDeduction() != null) {
            deductionTextInput.setText(selectedPayroll.getDeduction().toString());
        }

        deductionNotesTextInput.setText(selectedPayroll.getDeductionNotes());
    }

    private void registerFieldsClearErrorOnType() {
        Control[] controls = {payDateDatePicker, payMonthTextInput, payYearTextInput, amountTextInput, deductionTextInput, deductionNotesTextInput};
        for (Control control : controls) {
            clearErrorOnType(control);
        }
    }

    private void clearErrorOnType(Control field) {
        if (field instanceof TextField) {
            ((TextField) field).textProperty().addListener((observable, oldValue, newValue) -> errorLabel.setText(""));
        } else if (field instanceof DatePicker) {
            ((DatePicker) field).chronologyProperty().addListener((observable, oldValue, newValue) -> errorLabel.setText(""));
        }
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.payrollsWindow(selectedEmployee);
        }
    }

    public void savePayroll() {
        Payroll payroll;
        if (selectedPayroll != null) {
            payroll = selectedPayroll;
        } else {
            payroll = new Payroll();
        }

        try {
            payroll.setPayDate(Utils.toDate(payDateDatePicker.getValue()));
            payroll.setPayForMonth(Integer.parseInt(payMonthTextInput.getText()));
            payroll.setPayForYear(Integer.parseInt(payYearTextInput.getText()));
            payroll.setPayment(Double.parseDouble(amountTextInput.getText()));
            payroll.setDeduction(Double.parseDouble(deductionTextInput.getText()));
            payroll.setDeductionNotes(deductionNotesTextInput.getText());
            payroll.setEmployee(selectedEmployee);

            if (payrollService.createOrUpdateModel(payroll)) {
                main.payrollsWindow(selectedEmployee);
            } else {
                errorLabel.setText("Error in saving payroll data.");
            }
        } catch (Exception e) {
            errorLabel.setText("Error in saving payroll data.");
        }

    }

    public void deletePayroll() {
        if (selectedPayroll != null) {
            boolean result = payrollService.deletePayroll(selectedPayroll.getId());
            if (result) {
                main.payrollsWindow(selectedEmployee);
            }
        }
    }
}

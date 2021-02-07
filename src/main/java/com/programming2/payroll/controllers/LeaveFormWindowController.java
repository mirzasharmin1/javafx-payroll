package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.common.Utils;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import com.programming2.payroll.services.EmployeeService;
import com.programming2.payroll.services.LeaveService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaveFormWindowController  extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    private LeaveService leaveService;
    private EmployeeService employeeService;

    @FXML DatePicker startDateDatePicker;
    @FXML DatePicker endDateDatePicker;
    @FXML TextField leaveDaysTextField;
    @FXML TextField notesTextField;
    @FXML Label errorLabel;

    public LeaveFormWindowController() {
        leaveService = new LeaveService();
        employeeService = new EmployeeService();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Utils.setNumericFieldValidators(leaveDaysTextField);
        registerFieldsClearErrorOnType();
    }

    private void registerFieldsClearErrorOnType() {
        Control[] controls = {startDateDatePicker, endDateDatePicker, leaveDaysTextField, notesTextField};
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
            main.leavesWindow(selectedEmployee);
        }
    }

    public void saveLeave() {
        Leave leave = new Leave();

        int leaveDays = 0;
        try {
            leaveDays = Integer.parseInt(leaveDaysTextField.getText());
        } catch (Exception e) {
            errorLabel.setText("Leave days input is required.");
            return;
        }

        if (selectedEmployee.getLeaveBalance() - leaveDays >= 0)  {
            leave.setStartDate(Utils.toDate(startDateDatePicker.getValue()));
            leave.setEndDate(Utils.toDate(endDateDatePicker.getValue()));
            leave.setLeaveDays(leaveDays);
            leave.setNote(notesTextField.getText());
            leave.setEmployee(selectedEmployee);

            if (leaveService.createOrUpdateModel(leave)) {

                selectedEmployee.setLeaveBalance(selectedEmployee.getLeaveBalance() - leaveDays);

                if (employeeService.createOrUpdateModel(selectedEmployee)) {
                    main.leavesWindow(selectedEmployee);
                } else {
                    errorLabel.setText("Could not update employee leave balance.");
                }
            } else {
                errorLabel.setText("Could not save leave information.");
            }
        } else {
            errorLabel.setText("Leave balance is not sufficient.");
        }
    }
}

package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Payroll;
import com.programming2.payroll.services.PayrollService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class PayrollsWindowController extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    @FXML Button backButton;
    @FXML Button addPayrollButton;

    @FXML TableView<Payroll> payrollsTableView;
    @FXML TableColumn<Payroll, String> colPayDate;
    @FXML TableColumn<Payroll, Integer> colPayForMonth;
    @FXML TableColumn<Payroll, Integer> colPayForYear;
    @FXML TableColumn<Payroll, Double> colAmount;
    @FXML TableColumn<Payroll, Hyperlink> colActions;

    PayrollService payrollService;
    ObservableList<Payroll> payrollObservableList;

    public PayrollsWindowController() {
        payrollService = new PayrollService();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
        initAndSetLeaves();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableCellValueFactories();
    }

    private void initAndSetLeaves() {
        List<Payroll> payrolls = payrollService.getPayrollsForEmployee(selectedEmployee.getId());
        payrollObservableList = FXCollections.observableList(payrolls);
        payrollsTableView.setItems(payrollObservableList);
    }

    private void setTableCellValueFactories() {
        colPayDate.setCellValueFactory(cellData -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = df.format(cellData.getValue().getPayDate());
            return new ReadOnlyStringWrapper(dateStr);
        });

        colPayForMonth.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPayForMonth()));
        colPayForYear.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPayForYear()));
        colAmount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPayment()));

        colActions.setCellValueFactory(cellData -> {
            Payroll payroll = cellData.getValue();

            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText("Update");

            hyperlink.setOnAction(event -> navigateToUpdatePayrollWindow(payroll));

            return new ReadOnlyObjectWrapper<>(hyperlink);
        });
    }

    public void navigateToUpdatePayrollWindow(Payroll payroll) {
        if (selectedEmployee != null) {
            main.updatePayrollWindow(payroll, selectedEmployee);
        }
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.updateEmployeeWindow(selectedEmployee);
        }
    }

    public void addPayroll() {
        if (selectedEmployee != null) {
            main.createPayrollWindow(selectedEmployee);
        }
    }
}

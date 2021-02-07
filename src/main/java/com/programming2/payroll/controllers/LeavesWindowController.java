package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.Leave;
import com.programming2.payroll.services.LeaveService;
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

public class LeavesWindowController extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    @FXML Button backButton;
    @FXML Button addLeaveButton;
    @FXML TableView<Leave> leavesTableView;
    @FXML TableColumn<Leave, String> colStartDate;
    @FXML TableColumn<Leave, String> colEndDate;
    @FXML TableColumn<Leave, Integer> colLeaveDays;
    @FXML TableColumn<Leave, Hyperlink> colActions;

    LeaveService leaveService;
    ObservableList<Leave> leaveObservableList;

    public LeavesWindowController() {
        leaveService = new LeaveService();
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
        List<Leave> leaves = leaveService.getLeavesForEmployee(selectedEmployee.getId());
        leaveObservableList = FXCollections.observableList(leaves);
        leavesTableView.setItems(leaveObservableList);
    }

    private void setTableCellValueFactories() {
        colStartDate.setCellValueFactory(cellData -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = df.format(cellData.getValue().getStartDate());
            return new ReadOnlyStringWrapper(dateStr);
        });

        colEndDate.setCellValueFactory(cellData -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = df.format(cellData.getValue().getEndDate());
            return new ReadOnlyStringWrapper(dateStr);
        });

        colLeaveDays.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getLeaveDays()));

        colActions.setCellValueFactory(cellData -> {
            Leave leave = cellData.getValue();

            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText("View");

            hyperlink.setOnAction(event -> navigateToViewLeaveWindow(leave));

            return new ReadOnlyObjectWrapper<>(hyperlink);
        });
    }

    public void navigateToViewLeaveWindow(Leave leave) {
        if (selectedEmployee != null) {
            main.viewLeaveWindow(leave, selectedEmployee);
        }
    }

    public void goBack() {
        if (selectedEmployee != null) {
            main.updateEmployeeWindow(selectedEmployee);
        }
    }

    public void addLeave() {
        if (selectedEmployee != null) {
            main.createLeaveWindow(selectedEmployee);
        }
    }
}

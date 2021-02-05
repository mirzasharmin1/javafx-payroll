package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.Department;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.services.DepartmentService;
import com.programming2.payroll.services.EmployeeService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeesWindowController extends BaseController implements Initializable {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    private Main main;

    @FXML ComboBox<Department> departmentComboBox;
    @FXML TextField searchByNameTextField;

    @FXML TableView<Employee> employeesTableView;
    @FXML TableColumn<Employee, String> firstNameCol;
    @FXML TableColumn<Employee, String> lastNameCol;
    @FXML TableColumn<Employee, String> emailCol;
    @FXML TableColumn<Employee, String> positionCol;
    @FXML TableColumn<Employee, String> dateOfJoiningCol;
    @FXML TableColumn<Employee, String> departmentCol;
    @FXML TableColumn<Employee, Hyperlink> managerCol;
    @FXML TableColumn<Employee, Hyperlink> actionsCol;

    ObservableList<Department> departmentObservableList;
    ObservableList<Employee> employeeObservableList;

    public EmployeesWindowController() {
        departmentService = new DepartmentService();
        employeeService = new EmployeeService();

        fetchDepartments();
        fetchEmployees();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDepartmentComboBoxConverter();
        setDepartments();
        setEmployees();

        setTableCellValueFactories();

        setSearchByName();
        setSearchByDepartment();
    }

    private void fetchDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        departmentObservableList = FXCollections.observableList(departments);
    }

    private void fetchEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        employeeObservableList = FXCollections.observableList(employees);
    }

    private void setDepartments() {
        departmentObservableList.add(0, null);
        departmentComboBox.itemsProperty().set(departmentObservableList);
    }

    private void setEmployees() {
        employeesTableView.setItems(employeeObservableList);
    }

    private void setTableCellValueFactories() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        positionCol.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(
                        cellData.getValue().getPosition().name().replace("_", " ")
                )
        );
        dateOfJoiningCol.setCellValueFactory(cellData -> {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = df.format(cellData.getValue().getDateOfJoining());
            return new ReadOnlyStringWrapper(dateStr);
        });
        departmentCol.setCellValueFactory(cellData -> {
            Department dept = cellData.getValue().getDepartment();

            String deptName = "";
            if (dept != null) {
                deptName = dept.getName();
            }

            return new ReadOnlyStringWrapper(deptName);
        });
        managerCol.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();

            Employee manager = employee.getManager();

            Hyperlink hyperlink = null;
            if (manager != null) {
                hyperlink = new Hyperlink();
                hyperlink.setText(employee.getName());
            }
            return new ReadOnlyObjectWrapper<>(hyperlink);
        });
        actionsCol.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();

            Hyperlink hyperlink = new Hyperlink();
            hyperlink.setText("Update");

            return new ReadOnlyObjectWrapper<>(hyperlink);
        });
    }

    private void setSearchByName() {
        searchByNameTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                String name = searchByNameTextField.getText();

                List<Employee> employees;

                if (!name.trim().isEmpty()) {
                    employees = employeeService.getEmployeesByName(name.trim());
                } else {
                    employees = employeeService.getAllEmployees();
                }

                if (employees != null) {
                    employeeObservableList.clear();
                    employeeObservableList.addAll(employees);
                }
            }
        });
    }

    private void setSearchByDepartment() {
        departmentComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            List<Employee> employees;

            if (newValue != null) {
                employees = employeeService.getEmployeesForDepartment(newValue.getId());
            } else {
                employees = employeeService.getAllEmployees();
            }
            if (employees != null) {
                employeeObservableList.clear();
                employeeObservableList.addAll(employees);
            }
        });
    }

    private void setDepartmentComboBoxConverter() {
        departmentComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Department object) {
                if (object != null) {
                    return object.getName();
                }

                return null;
            }

            @Override
            public Department fromString(String string) {
                return departmentComboBox.getItems().stream().filter(department ->
                        department.getName().equals(string)
                ).findFirst().orElse(null);
            }
        });
    }
}

package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.common.Utils;
import com.programming2.payroll.models.Address;
import com.programming2.payroll.models.Department;
import com.programming2.payroll.models.Employee;
import com.programming2.payroll.models.EmployeePosition;
import com.programming2.payroll.services.AddressService;
import com.programming2.payroll.services.DepartmentService;
import com.programming2.payroll.services.EmployeeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormWindowController extends BaseController implements Initializable {

    private Main main;
    private Employee selectedEmployee;

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final AddressService addressService;

    ObservableList<Department> departmentObservableList;
    ObservableList<Employee> employeeObservableList;
    ObservableList<EmployeePosition> positionObservableList;

    @FXML Button backButton;
    @FXML Button payrollsButton;
    @FXML Button leavesButton;
    @FXML Button saveButton;
    @FXML Button deleteButton;
    @FXML TextField firstNameTextInput;
    @FXML TextField lastNameTextInput;
    @FXML DatePicker dateOfBirthDatePicker;
    @FXML TextField emailTextInput;
    @FXML TextField phoneNoTextInput;
    @FXML DatePicker joiningDateDatePicker;
    @FXML ComboBox<EmployeePosition> positionComboBox;
    @FXML ComboBox<Employee> managerComboBox;
    @FXML ComboBox<Department> departmentComboBox;
    @FXML TextField salaryTextInput;
    @FXML TextField leaveBalanceTextInput;
    @FXML TextField addressLine1TextInput;
    @FXML TextField addressLine2TextInput;
    @FXML TextField cityTextInput;
    @FXML TextField stateTextInput;
    @FXML TextField countryTextInput;
    @FXML TextField zipcodeTextInput;
    @FXML Label errorLabel;

    public EmployeeFormWindowController() {
        departmentService = new DepartmentService();
        employeeService = new EmployeeService();
        addressService = new AddressService();

        fetchDepartments();
        fetchEmployees();
        getEmployeePositions();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
        setInputValues();
        filterSelectedEmployees();

        payrollsButton.setVisible(true);
        leavesButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDepartmentComboBoxConverter();
        setEmployeeComboBoxConverter();
        setPositionComboBoxConverter();

        Utils.setNumericFieldValidators(salaryTextInput);
        Utils.setNumericFieldValidators(leaveBalanceTextInput);

        setDepartmentDropdownOptions();
        setEmployeeDropdownOptions();
        setPositionDropdownOptions();

        registerFieldsClearErrorOnType();

        payrollsButton.setVisible(false);
        leavesButton.setVisible(false);
        deleteButton.setVisible(false);
    }

    public void fetchDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        departmentObservableList = FXCollections.observableList(departments);
    }

    public void fetchEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        employeeObservableList = FXCollections.observableList(employees);
    }

    private void filterSelectedEmployees() {
        if (selectedEmployee != null) {
            employeeObservableList.removeIf(employee -> employee != null && employee.getId() == selectedEmployee.getId());
        }
    }

    public void getEmployeePositions() {
        List<EmployeePosition> positions = Arrays.asList(EmployeePosition.class.getEnumConstants());
        positionObservableList = FXCollections.observableList(positions);
    }

    public void setDepartmentDropdownOptions() {
        departmentComboBox.itemsProperty().set(departmentObservableList);
    }

    public void setEmployeeDropdownOptions() {
        employeeObservableList.add(0, null);
        managerComboBox.itemsProperty().set(employeeObservableList);
    }

    public void setPositionDropdownOptions() {
        positionComboBox.itemsProperty().set(positionObservableList);
    }

    public void goBackToEmployeesWindow() {
        main.employeesWindow();
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

    private void setEmployeeComboBoxConverter() {
        managerComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(Employee object) {
                if (object != null) {
                    return object.getName();
                }
                return null;
            }

            @Override
            public Employee fromString(String string) {
                return managerComboBox.getItems().stream().filter(employee ->
                        employee.getName().equals(string)
                ).findFirst().orElse(null);
            }
        });
    }

    public void setPositionComboBoxConverter() {
        positionComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(EmployeePosition object) {
                if (object != null) {
                    return object.toString().replace("_", " ");
                }
                return null;
            }

            @Override
            public EmployeePosition fromString(String string) {
                return positionComboBox.getItems().stream().filter(position ->
                        position.toString().replace("_", " ").equals(string)
                ).findFirst().orElse(null);
            }
        });
    }

    private void setInputValues() {
        if (selectedEmployee == null) {
            return;
        }

        firstNameTextInput.setText(selectedEmployee.getFirstName());
        lastNameTextInput.setText(selectedEmployee.getLastName());

        if (selectedEmployee.getDateOfBirth() != null) {
            dateOfBirthDatePicker.setValue(
                    selectedEmployee.getDateOfBirth().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
            );
        }

        emailTextInput.setText(selectedEmployee.getEmail());
        phoneNoTextInput.setText(selectedEmployee.getPhoneNo());

        if (selectedEmployee.getDateOfJoining() != null) {
            joiningDateDatePicker.setValue(
                selectedEmployee.getDateOfJoining().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
            );
        }

        if (selectedEmployee.getPosition() != null) {
            positionComboBox.setValue(selectedEmployee.getPosition());
        }

        if (selectedEmployee.getManager() != null) {
            managerComboBox.setValue(selectedEmployee.getManager());
        }

        if (selectedEmployee.getDepartment() != null) {
            departmentComboBox.setValue(selectedEmployee.getDepartment());
        }

        salaryTextInput.setText(selectedEmployee.getMonthlySalary().toString());
        leaveBalanceTextInput.setText(selectedEmployee.getLeaveBalance().toString());

        if (selectedEmployee.getAddress() != null) {
            addressLine1TextInput.setText(selectedEmployee.getAddress().getAddressLine1());
            addressLine2TextInput.setText(selectedEmployee.getAddress().getAddressLine2());
            cityTextInput.setText(selectedEmployee.getAddress().getCity());
            stateTextInput.setText(selectedEmployee.getAddress().getState());
            countryTextInput.setText(selectedEmployee.getAddress().getCountry());
            zipcodeTextInput.setText(selectedEmployee.getAddress().getZipCode());
        }
    }

    private void registerFieldsClearErrorOnType() {
        Control[] controls = {firstNameTextInput, lastNameTextInput, dateOfBirthDatePicker, emailTextInput, phoneNoTextInput, joiningDateDatePicker, positionComboBox, managerComboBox, departmentComboBox, salaryTextInput, leaveBalanceTextInput, addressLine1TextInput, addressLine2TextInput, cityTextInput, stateTextInput, countryTextInput, zipcodeTextInput};
        for (Control control : controls) {
            clearErrorOnType(control);
        }
    }

    private void clearErrorOnType(Control field) {
        if (field instanceof TextField) {
            ((TextField) field).textProperty().addListener((observable, oldValue, newValue) -> errorLabel.setText(""));
        } else if (field instanceof DatePicker) {
            ((DatePicker) field).chronologyProperty().addListener((observable, oldValue, newValue) -> errorLabel.setText(""));
        } else if (field instanceof ComboBox) {
            ((ComboBox<?>) field).itemsProperty().addListener((observable, oldValue, newValue) -> errorLabel.setText(""));
        }
    }

    public void createOrUpdateEmployee() {
        Employee employee;
        if (selectedEmployee != null) {
            employee = selectedEmployee;
        } else {
            employee = new Employee();
        }

        employee.setFirstName(firstNameTextInput.getText());
        employee.setLastName(lastNameTextInput.getText());
        employee.setDateOfBirth(Utils.toDate(dateOfBirthDatePicker.getValue()));
        employee.setEmail(emailTextInput.getText());
        employee.setPhoneNo(phoneNoTextInput.getText());
        employee.setDateOfJoining(Utils.toDate(joiningDateDatePicker.getValue()));
        employee.setPosition(positionComboBox.getValue());
        employee.setManager(managerComboBox.getValue());
        employee.setDepartment(departmentComboBox.getValue());
        employee.setMonthlySalary(Double.parseDouble(salaryTextInput.getText()));
        employee.setLeaveBalance(Integer.parseInt(leaveBalanceTextInput.getText()));

        if (employeeService.createOrUpdateModel(employee)) {
            if (createAndAttachAddress(employee)) {
                main.employeesWindow();
            }
        } else {
            errorLabel.setText("Could not save employee data, please check your inputs.");
        }
    }

    private boolean createAndAttachAddress(Employee employee) {
        Address address;
        if (employee.getAddress() == null) {
            address = new Address();
        } else {
            address = employee.getAddress();
        }

        address.setAddressLine1(addressLine1TextInput.getText());
        address.setAddressLine2(addressLine2TextInput.getText());
        address.setCity(cityTextInput.getText());
        address.setState(stateTextInput.getText());
        address.setCountry(countryTextInput.getText());
        address.setZipCode(zipcodeTextInput.getText());
        address.setEmployee(employee);

        return addressService.createOrUpdateModel(address);
    }

    public void deleteEmployee() {
        if (selectedEmployee != null) {
            boolean result = employeeService.deleteEmployee(selectedEmployee.getId());
            if (result) {
                main.employeesWindow();
            } else {
                errorLabel.setText("Could not delete employee.");
            }
        }
    }

    public void navigateToPayrolls() {
        if (selectedEmployee != null) {
            main.payrollsWindow(selectedEmployee);
        }
    }

    public void navigateToLeaves() {
        if (selectedEmployee != null) {
            main.leavesWindow(selectedEmployee);
        }
    }
}

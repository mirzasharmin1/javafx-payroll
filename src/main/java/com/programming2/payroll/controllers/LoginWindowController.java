package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import com.programming2.payroll.models.User;
import com.programming2.payroll.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

    private static final String ERROR_USER_NOT_FOUND = "User doesn't exist";
    private static final String ERROR_WRONG_PASSWORD = "Wrong password, please try again";
    private static final String ERROR_REQUIRED_DATA_MISSING = "Username and password are required";

    @FXML TextField usernameInput;
    @FXML PasswordField passwordInput;
    @FXML Label errorLabel;

    private UserService userService;

    public LoginWindowController() {
        userService = new UserService();
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    public void attemptLogin() {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        if (username.equals("") || password.equals("")) {
            errorLabel.setText(ERROR_REQUIRED_DATA_MISSING);
            return;
        }

        User user = userService.findUser(username);

        if (user == null) {
            errorLabel.setText(ERROR_USER_NOT_FOUND);
            return;
        }

        boolean isAuthenticated = userService.authenticate(user, password);

        if (!isAuthenticated) {
            errorLabel.setText(ERROR_WRONG_PASSWORD);
            return;
        }

        navigateToEmployeesWindow();
    }

    private void navigateToEmployeesWindow() {
        main.employeesWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupNonEmptyValidator(usernameInput);
        setupNonEmptyValidator(passwordInput);

        clearErrorOnType(usernameInput);
        clearErrorOnType(passwordInput);
    }

    private void clearErrorOnType(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            errorLabel.setText("");
        });
    }
}

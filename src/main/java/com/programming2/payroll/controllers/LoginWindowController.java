package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginWindowController extends BaseController implements Initializable {

    @FXML TextField usernameInput;
    @FXML PasswordField passwordInput;
    @FXML Label errorLabel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupNonEmptyValidator(usernameInput);
        setupNonEmptyValidator(passwordInput);
    }
}

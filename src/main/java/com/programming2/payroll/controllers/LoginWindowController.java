package com.programming2.payroll.controllers;

import com.programming2.payroll.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginWindowController {

    @FXML TextField usernameInput;
    @FXML PasswordField passwordInput;
    @FXML Label errorLabel;

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }
}

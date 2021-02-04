package com.programming2.payroll.controllers;

import javafx.scene.control.TextField;

abstract class BaseController {

    void setupNonEmptyValidator(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (field.getText() == null || field.getText().equals("")) {
                field.getStyleClass().add("error-control");
            } else {
                field.getStyleClass().remove("error-control");
            }
        });
    }
}

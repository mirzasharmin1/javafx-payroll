package com.programming2.payroll.controls;

import javafx.scene.control.TextField;

interface TextNonEmptyValidator {

    default void setupNonEmptyValidator(TextField field) {
        field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (field.getText() == null || field.getText().equals("")) {
                field.getStyleClass().add("error-control");
            } else {
                field.getStyleClass().remove("error-control");
            }
        });
    }
}

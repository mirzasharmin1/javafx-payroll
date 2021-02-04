package com.programming2.payroll.controls;

import javafx.scene.control.PasswordField;

public class NonEmptyPasswordField extends PasswordField implements TextNonEmptyValidator {

    public NonEmptyPasswordField() {
        super();
        setupNonEmptyValidator(this);
    }

    public boolean isValid() {
        return this.getText() != null && !this.getText().equals("");
    }
}

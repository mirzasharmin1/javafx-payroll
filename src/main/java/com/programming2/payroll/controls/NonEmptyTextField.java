package com.programming2.payroll.controls;

import javafx.scene.control.TextField;

public class NonEmptyTextField extends TextField implements TextNonEmptyValidator {

    public NonEmptyTextField() {
        super();
        setupNonEmptyValidator(this);
    }

    public boolean isValid() {
        return this.getText() != null && !this.getText().equals("");
    }
}

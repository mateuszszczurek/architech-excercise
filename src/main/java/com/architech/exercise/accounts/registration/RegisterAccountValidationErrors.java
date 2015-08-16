package com.architech.exercise.accounts.registration;

import com.architech.exercise.accounts.registration.constraints.ValidationErrors;

import java.util.List;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class RegisterAccountValidationErrors {

    private final List<String> usernameErrors;
    private final List<String> passwordErrors;

    public RegisterAccountValidationErrors(ValidationErrors validationErrors) {
        usernameErrors = validationErrors.valuesForField("username");
        passwordErrors = validationErrors.valuesForField("password");
    }

    public List<String> getUsernameErrors() {
        return usernameErrors;
    }

    public List<String> getPasswordErrors() {
        return passwordErrors;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

}

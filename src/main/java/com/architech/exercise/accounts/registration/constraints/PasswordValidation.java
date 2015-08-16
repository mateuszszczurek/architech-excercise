package com.architech.exercise.accounts.registration.constraints;

import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.isEmpty;

class PasswordValidation implements Validation {

    public static final String PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty";
    public static final String PASSWORD_CONSTRAINT = "Password must have at least 8 characters, 1 number, 1 uppercase character and 1 lowercase character";

    private static final String FIELD_NAME = "password";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d!#$%&? \"]{8,}$");

    private final String password;

    private PasswordValidation(String password) {
        this.password = password;
    }

    public static PasswordValidation passwordValidation(String password) {
        return new PasswordValidation(password);
    }

    @Override
    public ValidationErrors validate() {
        ValidationErrors validationErrors = new ValidationErrors();

        if (isEmpty(password)) {
            validationErrors.validationErrorFor(FIELD_NAME, PASSWORD_CANNOT_BE_EMPTY);
            return validationErrors;
        }

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            validationErrors.validationErrorFor(FIELD_NAME, PASSWORD_CONSTRAINT);
        }

        return validationErrors;
    }
}

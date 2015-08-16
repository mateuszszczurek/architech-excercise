package com.architech.exercise.accounts.registration.constraints;

import java.util.regex.Pattern;

import static org.springframework.util.StringUtils.isEmpty;

public class UsernameValidation implements Validation {

    public static final String USERNAME_EMPTY = "Username cannot be empty";
    public static final String USERNAME_LENGTH = "Username should  have at least 5 characters";
    public static final String USERNAME_CONSTRAINTS = "Username should contain only A-Z letters and 0-9 digits";

    private static final String USERNAME_FIELD = "username";

    private Pattern ALFANUMERIC_PATTERN = Pattern.compile("^[A-Za-z0-9]+$");

    private final String username;

    private UsernameValidation(String username) {
        this.username = username;
    }

    public static UsernameValidation usernameValiation(String username) {
        return new UsernameValidation(username);
    }

    @Override
    public ValidationErrors validate() {
        ValidationErrors validationErrors = new ValidationErrors();

        if (isEmpty(username)) {
            validationErrors.validationErrorFor(USERNAME_FIELD, USERNAME_EMPTY);
            return validationErrors;
        }

        if (username.length() < 5) {
            validationErrors.validationErrorFor("username", USERNAME_LENGTH);
        }

        if (!ALFANUMERIC_PATTERN.matcher(username).matches()) {
            validationErrors.validationErrorFor("username", USERNAME_CONSTRAINTS);
        }

        return validationErrors;
    }
}

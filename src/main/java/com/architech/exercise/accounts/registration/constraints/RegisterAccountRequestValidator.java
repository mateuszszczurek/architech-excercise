package com.architech.exercise.accounts.registration.constraints;

import com.architech.exercise.accounts.registration.RegisterAccountRequest;
import org.springframework.stereotype.Component;

import static com.architech.exercise.accounts.registration.constraints.PasswordValidation.passwordValidation;
import static com.architech.exercise.accounts.registration.constraints.UsernameValidation.usernameValiation;


@Component
public class RegisterAccountRequestValidator {

    public ValidationErrors validate(RegisterAccountRequest request) {

        UsernameValidation usernameValidation = usernameValiation(request.getUsername());
        PasswordValidation passwordValidation = passwordValidation(request.getPassword());

        return usernameValidation.validate().merge(passwordValidation.validate());
    }

}
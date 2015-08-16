package com.architech.exercise.accounts.registration;

import com.architech.exercise.accounts.registration.constraints.RegisterAccountRequestValidator;
import com.architech.exercise.accounts.registration.constraints.ValidationErrors;
import com.architech.exercise.accounts.users.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Registration {

    public static final String REGISTRATION_URL = "accounts/register";

    @Autowired
    private RegisterAccountRequestValidator validator;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(method = POST, path = REGISTRATION_URL, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterAccountValidationErrors> registerUserAccount(@RequestBody @Validated RegisterAccountRequest registerAccountRequest) {

        ValidationErrors validationErrors = validator.validate(registerAccountRequest);

        if(validationErrors.containsErrors()) {
            return new ResponseEntity<>(new RegisterAccountValidationErrors(validationErrors), HttpStatus.PRECONDITION_FAILED);
        }

        try {
            registrationService.register(registerAccountRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsernameAlreadyExistsException e) {
            validationErrors.validationErrorFor("username", "Username is already registered");
            return new ResponseEntity(new RegisterAccountValidationErrors(validationErrors), HttpStatus.CONFLICT);
        }

    }

}

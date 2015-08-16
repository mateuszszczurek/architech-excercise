package com.architech.exercise.accounts.registration

import com.architech.exercise.accounts.registration.constraints.RegisterAccountRequestValidator
import com.architech.exercise.accounts.registration.constraints.ValidationErrors
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.springframework.http.HttpStatus.OK

class RegistrationTest extends Specification {

    public static final ValidationErrors NO_ERRORS = new ValidationErrors()
    def validator = Mock(RegisterAccountRequestValidator)
    def registrationService = Mock(RegistrationService);
    def registration

    def setup() {
        registration = new Registration()
        registration.validator = validator
        registration.registrationService = registrationService
    }

    def "request should be validated"() {
        given:
        def request = new RegisterAccountRequest("username", "password")

        when:
        def result = registration.registerUserAccount(request)

        then:
        1 * validator.validate(request) >> new ValidationErrors()
        result.statusCode == OK
    }

    def "if the validation yields errors, will reply with validation errors"() {
        given:
        def request = new RegisterAccountRequest("username", "password")

        def validationErrors = new ValidationErrors()
        validationErrors.validationErrorFor("username", "some error message")

        def resultingError = new RegisterAccountValidationErrors(validationErrors)

        when:
        def result = registration.registerUserAccount(request)

        then:
        1 * validator.validate(request) >> validationErrors
        result.statusCode == HttpStatus.PRECONDITION_FAILED
        result.body == resultingError
    }

    def "if the user was already registered, appropriate validation error will be returned"() {
        given:
        def username = "username"
        def request = new RegisterAccountRequest(username, "irrelevant password")

        def validationErrors = new ValidationErrors()
        validationErrors.validationErrorFor(username, "User already registered")

        def expectedResult = new RegisterAccountValidationErrors(validationErrors)

        when:
        def result = registration.registerUserAccount(request)

        then:
        1 * validator.validate(request) >> NO_ERRORS
        1 * registrationService.register(_) >> { throw new UsernameAlreadyExistsException(username) }
        result.statusCode == HttpStatus.CONFLICT
        result.body == expectedResult
    }


}

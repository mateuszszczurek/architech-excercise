package com.architech.exercise.accounts.registration.constraints

import spock.lang.Specification

class ValidationErrorsTest extends Specification {

    def "can say if there are no validation errors"() {
        given:
        def errors = new ValidationErrors()

        expect:
        errors.containsErrors() == false
    }

    def "when validation error is added, can say that there were errors"() {
        given:
        def errors = new ValidationErrors()
        errors.validationErrorFor("someField", "someMessage")

        expect:
        errors.containsErrors() == true
    }

    def "can be merged"() {
        given:
        def errors = new ValidationErrors()
        errors.validationErrorFor("field", "message")
        def otherErrors = new ValidationErrors()
        otherErrors.validationErrorFor("otherField", "otherMessage")

        when:
        def result = errors.merge(otherErrors)

        then:
        result.size() == 2
    }

}

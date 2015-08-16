package com.architech.exercise.accounts.registration.constraints

import spock.lang.Specification
import spock.lang.Unroll

import static com.architech.exercise.accounts.registration.constraints.PasswordValidation.*

class PasswordValidationTest extends Specification {

    @Unroll
    def "constraints for password"(def password, def expectedMessages) {
        given:
        def validation = passwordValidation(password)

        when:
        def result = validation.validate()

        then:
        result.valuesForField("password") == expectedMessages
        where:
        password            || expectedMessages
        "short"             || [PASSWORD_CONSTRAINT]
        "longbutnodigit"    || [PASSWORD_CONSTRAINT]
        "longwithdigit1"    || [PASSWORD_CONSTRAINT]
        "longAndUpperCase"  || [PASSWORD_CONSTRAINT]
        "sHoRt1"            || [PASSWORD_CONSTRAINT]
        ""                  || [PASSWORD_CANNOT_BE_EMPTY]
        null                || [PASSWORD_CANNOT_BE_EMPTY]
    }

    @Unroll
    def "[#password] correct password must contain 8 alpha-numeric values, 1 number, 1 uppercase and 1 lowercase"(password) {
        given:
        def validation = passwordValidation(password)

        when:
        def result = validation.validate()

        then:
        result.containsErrors() == false

        where:
        password << ["longAndHasDigit1", "twoDigits12", "oneUppercase1", "acceptsSpecialSigns1#?", "someMore3xotic!@#)*(%^Ě,ľ"]
    }

}

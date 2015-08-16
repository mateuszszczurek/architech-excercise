package com.architech.exercise.accounts.registration.constraints
import spock.lang.Specification
import spock.lang.Unroll

import static com.architech.exercise.accounts.registration.constraints.UsernameValidation.*

class UsernameValidationTest extends Specification {

    @Unroll
    def "constraints for username [#username]"(def username, def expectedMessages) {
        given:
        def validation = usernameValiation(username)

        when:
        def result = validation.validate()

        then:
        result.valuesForField("username") == expectedMessages
        where:
        username       || expectedMessages
        ""             || [USERNAME_EMPTY]
        null           || [USERNAME_EMPTY]
        "shrt"         || [USERNAME_LENGTH]
        "white space"  || [USERNAME_CONSTRAINTS]
        "special_sign" || [USERNAME_CONSTRAINTS]
    }

    @Unroll
    def "correct username should be at least 5 chars long"(username) {
        given:
        def validation = usernameValiation(username)

        when:
        def result = validation.validate()

        then:
        result.containsErrors() == false

        where:
        username << ["validusername", "oThErVaLiD", "noConstraintsOverTheTotalLengthOfUsernameWhichCanBeSubmittedMaybeMemoryOnly"]
    }

}

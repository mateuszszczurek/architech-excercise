package com.architech.exercise.accounts.registration

import spock.lang.Specification

import static java.util.Optional.empty
import static java.util.Optional.of

class RegistrationServiceTest extends Specification {

    def userDao = Mock(UsersDao)
    def service

    def setup() {
        service = new RegistrationService()
        service.usersDao = userDao
    }

    def "if user is already registered, an exception will be thrown"() {
        when:
        service.register(new RegisterAccountRequest("username", "password"))

        then:
        1 * userDao.findUser(_) >> of(new User("username", "password"))
        thrown UsernameAlreadyExistsException
    }

    def "if user is not registered, will be persisted"() {
        when:
        service.register(new RegisterAccountRequest("username", "password"))

        then:
        1 * userDao.findUser(_) >> empty()
        1 * userDao.storeUser(new User("username", "password"))
    }

}

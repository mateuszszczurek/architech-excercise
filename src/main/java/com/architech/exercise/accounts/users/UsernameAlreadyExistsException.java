package com.architech.exercise.accounts.users;

public class UsernameAlreadyExistsException extends Exception {

    private final String username;

    public UsernameAlreadyExistsException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}

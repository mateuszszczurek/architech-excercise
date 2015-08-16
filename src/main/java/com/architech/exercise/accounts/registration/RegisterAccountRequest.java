package com.architech.exercise.accounts.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterAccountRequest {

    private final String accountName;

    private final String password;

    @JsonCreator
    public RegisterAccountRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.accountName = username;
        this.password = password;
    }

    public String getUsername() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

}

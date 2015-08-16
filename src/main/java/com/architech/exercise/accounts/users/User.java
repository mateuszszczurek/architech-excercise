package com.architech.exercise.accounts.users;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class User {

    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    static User userFor(String line) {
        String[] usernameAndPassword = line.split(",");
        return new User(usernameAndPassword[0], usernameAndPassword[1]);
    }

    @Override
    public boolean equals(Object o) {
       return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}

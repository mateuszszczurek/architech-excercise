package com.architech.exercise.accounts.users;

import java.util.Optional;

public interface UsersDao {

    public Optional<User> findUser(String username);

    void storeUser(User user);
}

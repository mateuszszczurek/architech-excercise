package com.architech.exercise.accounts.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static com.google.common.io.Files.createParentDirs;
import static java.lang.String.format;
import static java.nio.file.Files.*;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.util.Optional.empty;

@Repository
public class FileBasedUsersDao implements UsersDao {

    @Autowired
    private Path pathToUsersFile;

    public Optional<User> findUser(String username) {
        if(!exists(pathToUsersFile)) {
           return empty();
        }

        try {
            return lines(pathToUsersFile)
                    .map(User::userFor)
                    .filter(user -> username.equals((user.getUsername())))
                    .findFirst();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load users file. Something is wrong...");
        }
    }

    @Override
    public void storeUser(User user) {
        try {
            prepareDirectories();
            try (BufferedWriter writer = newBufferedWriter(pathToUsersFile, APPEND, CREATE)) {
                writer.write(format("%s,%s", user.getUsername(), user.getPassword()));
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Got IO Exception. Something is wrong...", e);
        }
    }


    private void prepareDirectories() throws IOException {
        if (!exists(pathToUsersFile.getParent())) {
            createParentDirs(pathToUsersFile.toFile());
        }
    }
}
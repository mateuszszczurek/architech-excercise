package com.architext.accounts.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static com.google.common.io.Files.createParentDirs;
import static org.apache.commons.io.FileUtils.write;

@Component
public class Users {

    @Autowired
    private Path usersFile;

    public void noUsers() {
        deleteUsers();
    }

    public void userAlreadyRegistered(String validUsername) {
        try {
            deleteUsers();
            createParentDirs(usersFile.toFile());
            write(usersFile.toFile(), userLine(validUsername));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUsers() {
        try {
            new FileOutputStream(usersFile.toFile(), false).close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String userLine(String validUsername) {
        return validUsername + "," + "somePassword";
    }
}

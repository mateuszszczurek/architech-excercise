package com.architext.accounts.registration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

import static java.nio.file.Paths.get;

@Configuration
public class LocalFileConfig {

    @Bean
    public Path pathToUsersFile() {
        return get("build/local_persistance/users.txt");
    }

    @Bean
    public Users users() {
        return new Users();
    }

}

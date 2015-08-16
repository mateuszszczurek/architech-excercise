package com.architech.exercise;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import java.nio.file.Path;

import static java.nio.file.Paths.get;

@Configuration
public class FileBasedDaoConfig {

    @Bean
    public Path pathToUsersFile(ServletContext servletContext) {
        return get(servletContext.getRealPath("/persistance/users.txt"));
    }


}

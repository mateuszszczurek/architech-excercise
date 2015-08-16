package com.architech.exercise;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectToRegistration {

    @RequestMapping("/")
    public String home() {
        return "register.html";
    }

}


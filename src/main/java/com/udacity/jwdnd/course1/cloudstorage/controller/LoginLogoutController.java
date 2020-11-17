package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginLogoutController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }




}

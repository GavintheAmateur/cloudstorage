package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginLogoutController {
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }




}

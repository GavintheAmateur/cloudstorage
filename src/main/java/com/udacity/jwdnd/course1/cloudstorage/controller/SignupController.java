package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.domain.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String signup() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute AppUser appUser, Model model) {
        String signupError = null;
        if (!userService.isUsernameAvailable(appUser.getUsername())) {
            signupError = "The username already exists.";
        }
        if (signupError == null) {
            try {
                AppUser user = userService.createUser(appUser);
            }
            catch(Exception e) {
                signupError = "There was an error signing you up. Please try again: "+e;
            }
        }
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);

        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}

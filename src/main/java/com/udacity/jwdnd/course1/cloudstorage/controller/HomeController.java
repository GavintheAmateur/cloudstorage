package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.AppUserFile;
import com.udacity.jwdnd.course1.cloudstorage.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/home","/"})
public class HomeController {

    private MessageListService messageListService;
    private AppUserService appUserService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public HomeController(MessageListService messageListService, AppUserService appUserService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.messageListService = messageListService;
        this.appUserService = appUserService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping("/error")
    public String error() {
            return "error";
        }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping()
    public String getHome(Authentication auth, Model model) {
        String username = auth.getPrincipal().toString();
        System.out.println("kkaa:"+username);
        if (username.isEmpty()) {
            return "login";
        }
        try {
            AppUser user = appUserService.loadUserByUsername(username);
            Long userId = user.getId();
            model.addAttribute("appUserFiles",fileService.getUserFileList(userId));
            model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));
            model.addAttribute("notes", noteService.getNotesByUserId(userId));
            return "home";
        } catch(NullPointerException npe) {

            return "redirect:/logout";
        }


    }
}

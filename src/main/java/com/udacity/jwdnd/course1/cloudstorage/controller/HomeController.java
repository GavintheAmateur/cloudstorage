package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.AppUserFile;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.MessageListService;
import org.apache.ibatis.jdbc.Null;
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
    public HomeController(MessageListService messageListService, AppUserService appUserService,FileService fileService) {
        this.messageListService = messageListService;
        this.appUserService = appUserService;
        this.fileService = fileService;
    }

    @GetMapping("/error")
    public String error() {
            return "error";
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
            List<AppUserFile> list = fileService.getUserFileList(userId);
            model.addAttribute("appUserFiles",list);
            model.addAttribute("activeTab","file");
            return "home";
        } catch(NullPointerException npe) {
            return "redirect:/logout";
        }


    }
}

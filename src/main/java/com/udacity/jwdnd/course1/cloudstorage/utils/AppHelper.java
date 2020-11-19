package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppHelper {
    @Autowired
    private AppUserService appUserService;

    private static AppUserService service;

    public AppHelper(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostConstruct
    public void init() {
        if(service == null) {
            service = appUserService;
        }
    }
    public  static Long getUserIdFromAuth(Authentication auth) {
        String username = auth.getPrincipal().toString();

        AppUser user = service.loadUserByUsername(username);
        Long userId = user.getId();
        return userId;
    }
}

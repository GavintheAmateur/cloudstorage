package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private AppUserService appUserService;

    public CredentialController(CredentialService credentialService,AppUserService appUserService) {
        this.credentialService = credentialService;
        this.appUserService =appUserService;
    }

    @PutMapping()
    public String createOrUpdateCredential(Credential credential, Authentication auth, Model model){
        String username = auth.getPrincipal().toString();
        AppUser user = appUserService.loadUserByUsername(username);
        Long userId = user.getId();
        credential.setUserId(userId);

        credentialService.addOrUpdateCredential(credential);

        model.addAttribute("credentials", credentialService.getCredentialsByUserId(userId));
        model.addAttribute("activeTab","credential");
        return "home";
    }


    @DeleteMapping()
    public String deleteCredential(@RequestParam Long id){
        credentialService.deleteCredentialById(id);
        return "home";
    }

}

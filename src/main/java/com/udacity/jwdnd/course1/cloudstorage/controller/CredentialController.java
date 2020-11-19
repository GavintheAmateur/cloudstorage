package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileStorageException;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.utils.AppHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService, AppUserService appUserService) {
        this.credentialService = credentialService;
    }

    @PutMapping()
    public String createOrUpdateCredential(Credential credential, Authentication auth, Model model) {
        Long userId = AppHelper.getUserIdFromAuth(auth);
        credential.setUserId(userId);

        try {
            credentialService.addOrUpdateCredential(credential);
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex.getMessage());
        } finally {
            model.addAttribute("activeTab", "credential");
            return "result";
        }

    }


    @DeleteMapping()
    public String deleteCredential(@RequestParam Long id,Model model) {
        try {
            credentialService.deleteCredentialById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex.getMessage());
        } finally {
            model.addAttribute("activeTab", "credential");
            return "result";
        }

    }

}

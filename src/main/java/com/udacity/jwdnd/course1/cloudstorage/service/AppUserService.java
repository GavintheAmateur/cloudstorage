package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.repository.AppUserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class AppUserService implements UserDetailsService, AuthenticationProvider {
    private final AppUserRepository appUserRepository;
    private final HashService hashService;

    public AppUserService(AppUserRepository appUserRepository, HashService hashService)  {
        this.appUserRepository = appUserRepository;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return appUserRepository.findByUsername(username) == null;
    }

    public AppUser createUser(AppUser appUser) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(appUser.getPassword(), encodedSalt);
        appUser.setPassword(hashedPassword);
        appUser.setSalt(encodedSalt);
        return appUserRepository.save(appUser);
    }


    @Override
    public AppUser loadUserByUsername(String s) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(s);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) {
            String encodedSalt = appUser.getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);
            if (appUser.getPassword().equals((hashedPassword))) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());

            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

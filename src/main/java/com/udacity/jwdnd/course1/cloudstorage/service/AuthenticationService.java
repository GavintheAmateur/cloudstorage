package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.domain.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {
    private UserRepository userRepository;
    private HashService hashService;

    public AuthenticationService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AppUser appUser = userRepository.findByUsername(username);
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

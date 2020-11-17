package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.repository.CredentialRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;
    private EncryptionService encryptionService;

    public CredentialService(CredentialRepository credentialRepository, EncryptionService encryptionService) {
        this.credentialRepository = credentialRepository;
        this.encryptionService = encryptionService;
    }

    public void addOrUpdateCredential(Credential credential) {
        String password = credential.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setEncodedKey(encodedKey);
        credential.setPlainPassword(null);
        credentialRepository.save(credential);
    }

    public void deleteCredentialById(Long id) {
        credentialRepository.deleteById(id);
    }

    public Object getCredentialsByUserId(Long userId) {
        List<Credential> credentials = credentialRepository.getCredentialsByUserId(userId);
        for (Credential c:credentials
             ) {
            String plainPassword = encryptionService.decryptValue(c.getPassword(), c.getEncodedKey());
            c.setPlainPassword(plainPassword);
        }
        return credentials;
    }
}

package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential extends  BaseEntity implements Serializable {

    private String url;
    private String username;
    private String plainPassword;
    private String password;
    private Long userId;
    private String encodedKey;

    @Builder
    public Credential(Long id, String url,String username,String password,String encodedKey,Long userId) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
        this.encodedKey = encodedKey;
        this.userId = userId;
    }
}

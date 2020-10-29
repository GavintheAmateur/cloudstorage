package com.udacity.jwdnd.course1.cloudstorage.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser extends BaseEntity implements Serializable {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String salt;
}

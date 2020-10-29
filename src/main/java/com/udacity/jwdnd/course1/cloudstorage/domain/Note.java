package com.udacity.jwdnd.course1.cloudstorage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note extends BaseEntity implements Serializable {
    private String noteTitle;
    private String noteDescription;
    private Integer userId;

}
package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.*;

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
    private Long userId;

    @Builder
    public Note(Long id, String noteTitle, String noteDescription,Long userId){
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    @Builder
    public Note(Long id){
        this.id = id;
    }
}
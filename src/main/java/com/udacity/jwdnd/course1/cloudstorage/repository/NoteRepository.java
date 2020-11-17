package com.udacity.jwdnd.course1.cloudstorage.repository;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note,Long> {

    @Query("select new Note(id,noteTitle,noteDescription,userId) from Note where userId= ?1")
    public List<Note> getNotesByUserId(Long userId);


}

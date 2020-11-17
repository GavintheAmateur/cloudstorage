package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void addOrUpdateNote(Note note) {
        noteRepository.save(note);
    }

    public List<Note> getNotesByUserId(Long userId) {
        return noteRepository.getNotesByUserId(userId);

    }


    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }


}

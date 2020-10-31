package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.jwdnd.course1.cloudstorage.domain.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.domain.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;
    @Autowired
    ObjectMapper objectMapper;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public String addNote(Note note, Authentication authentication){
        AppUser appUser = (AppUser) authentication.getPrincipal();
        note.setUserId(appUser.getId());
        noteService.addNote(note);
        return "home";
    }
}

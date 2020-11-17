package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;
    private AppUserService appUserService;

    public NoteController(NoteService noteService,AppUserService appUserService) {
        this.noteService = noteService;
        this.appUserService =appUserService;
    }

    @GetMapping
    public String getFile(@PathVariable Long id) {
            return "home";
    }

    @PutMapping()
    public String createOrUpdateNote(Note note, Authentication auth, Model model){
        String username = auth.getPrincipal().toString();
        AppUser user = appUserService.loadUserByUsername(username);
        Long userId = user.getId();
        note.setUserId(userId);

        noteService.addOrUpdateNote(note);

        model.addAttribute("notes", noteService.getNotesByUserId(userId));
        model.addAttribute("activeTab","note");
        return "home";
    }

    @DeleteMapping()
    public String deleteNote(@RequestParam Long id){
        noteService.deleteNoteById(id);
        return "home";
    }

}

package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.service.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.AppHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService,AppUserService appUserService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String getFile(@PathVariable Long id) {
            return "home";
    }

    @PutMapping()
    public String createOrUpdateNote(Note note, Authentication auth, Model model){
        Long userId = AppHelper.getUserIdFromAuth(auth);
        note.setUserId(userId);
        try {
            noteService.addOrUpdateNote(note);
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex.getMessage());
        } finally {
            model.addAttribute("activeTab", "note");
            return "result";
        }

    }

    @DeleteMapping()
    public String deleteNote(@RequestParam Long id, Model model){
        try {
            noteService.deleteNoteById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMsg", ex.getMessage());
        } finally {
            model.addAttribute("activeTab", "note");
            return "result";
        }
    }

}

package com.notepad.web.controller;

import com.notepad.web.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping(value = "/create")
    public String create(@RequestParam Integer notebookId, @RequestParam String noteName) {
        noteService.create(notebookId, noteName);
        return "redirect:/";
    }

    @PostMapping(value = "/rename")
    public String rename(@RequestParam Integer noteId,
                         @RequestParam String noteNewName) {

        noteService.rename(noteId, noteNewName);
        return "redirect:/";
    }

    @PostMapping(value = "/update")
    public String update(@RequestParam Integer noteId, @RequestParam String content) {
        noteService.update(noteId, content);
        return "redirect:/";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Integer noteId) {
        noteService.delete(noteId);
        return "redirect:/";
    }
}

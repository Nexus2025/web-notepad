package com.notepad.web.controller;

import com.notepad.web.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/note")
public class UiNoteController {

    @Autowired
    private NoteService noteService;

    private final static Integer USER_ID = 1;

    @PostMapping(value = "/create")
    public RedirectView create(@RequestParam Integer notebookId, @RequestParam String noteName,
                               RedirectAttributes attributes) {

        noteService.create(notebookId, noteName, USER_ID);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/rename")
    public RedirectView rename(@RequestParam Integer noteId, @RequestParam String noteNewName,
                               @RequestParam Integer notebookId,
                               RedirectAttributes attributes) {

        noteService.rename(noteId, noteNewName, USER_ID);
        attributes.addAttribute("notebookId", notebookId);
        attributes.addAttribute("noteId", noteId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/update")
    public RedirectView update(@RequestParam Integer noteId, @RequestParam String content,
                               @RequestParam Integer notebookId,
                               RedirectAttributes attributes) {

        noteService.update(noteId, content, USER_ID);
        attributes.addAttribute("notebookId", notebookId);
        attributes.addAttribute("noteId", noteId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/delete")
    public RedirectView delete(@RequestParam Integer noteId, @RequestParam Integer notebookId,
                               RedirectAttributes attributes) {

        noteService.delete(noteId, USER_ID);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }
}

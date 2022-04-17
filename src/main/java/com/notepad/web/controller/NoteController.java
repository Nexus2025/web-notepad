package com.notepad.web.controller;

import com.notepad.web.service.NoteService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    public RedirectView create(@RequestParam Integer notebookId, @RequestParam String noteName,
                               RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.create(notebookId, noteName, userId);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/rename")
    public RedirectView rename(@RequestParam Integer noteId, @RequestParam String noteNewName,
                               @RequestParam Integer notebookId, RedirectAttributes attributes,
                               Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.rename(noteId, noteNewName, userId);
        attributes.addAttribute("notebookId", notebookId);
        attributes.addAttribute("noteId", noteId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/update")
    public RedirectView update(@RequestParam Integer noteId, @RequestParam String content,
                               @RequestParam Integer notebookId, RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.update(noteId, content, userId);
        attributes.addAttribute("notebookId", notebookId);
        attributes.addAttribute("noteId", noteId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/delete")
    public RedirectView delete(@RequestParam Integer noteId, @RequestParam Integer notebookId,
                               RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.delete(noteId, userId);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }
}

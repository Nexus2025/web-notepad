package com.notepad.web.controller;

import com.notepad.web.entity.Note;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger log = LoggerFactory.getLogger(NoteController.class);

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    @Transactional
    public RedirectView create(@RequestParam Integer notebookId, @RequestParam String noteName,
                               RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        Note note = noteService.create(noteName, notebookId, userId);
        attributes.addAttribute("notebookId", note.getNotebookId());

        return new RedirectView("/");
    }

    @PostMapping(value = "/rename")
    @Transactional
    public RedirectView rename(@RequestParam Integer noteId, @RequestParam String noteNewName,
                               @RequestParam Integer notebookId, RedirectAttributes attributes,
                               Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        Note note = noteService.rename(noteId, noteNewName, userId);
        attributes.addAttribute("notebookId", note.getNotebookId());
        attributes.addAttribute("noteId", note.getId());

        return new RedirectView("/");
    }

    @PostMapping(value = "/update")
    @Transactional
    public RedirectView updateContent(@RequestParam(name = "noteId") Integer noteId, @RequestParam String content,
                                      @RequestParam Integer notebookId, RedirectAttributes attributes,
                                      Principal principal) {

        System.out.println(content);

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.updateContent(noteId, content, userId);
        attributes.addAttribute("notebookId", notebookId);
        attributes.addAttribute("noteId", noteId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/delete")
    @Transactional
    public RedirectView delete(@RequestParam Integer noteId, @RequestParam Integer notebookId,
                               RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.delete(noteId, userId);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/getContent", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getContent(Principal principal, @RequestParam Integer noteId) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        Note note = noteService.get(noteId, userId);

        return note.getContent();
    }
}

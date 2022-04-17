package com.notepad.web.controller;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.NotebookService;
import com.notepad.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAll(Model model, Principal principal,
                         @RequestParam(required = false)Integer notebookId,
                         @RequestParam(required = false)Integer noteId) {

        Integer userId = userService.findByUserName(principal.getName()).getId();

        List<Notebook> notebooks = notebookService.getAll(userId);
        model.addAttribute("notebooks", notebooks);
        model.addAttribute("currentNotebook", notebookService.get(notebookId, userId));
        model.addAttribute("notes", noteService.getAllByNotebookId(notebookId, userId));
        model.addAttribute("currentNote", noteService.get(noteId, userId));
        return "main";
    }
}

package com.notepad.web.controller;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UiRootController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    private final static Integer USER_ID = 1;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false)Integer notebookId,
                         @RequestParam(required = false)Integer noteId) {

        List<Notebook> notebooks = notebookService.getAll(USER_ID);
        model.addAttribute("notebooks", notebooks);
        model.addAttribute("currentNotebook", notebookService.get(notebookId, USER_ID));
        model.addAttribute("notes", noteService.getAllByNotebookId(notebookId, USER_ID));
        model.addAttribute("currentNote", noteService.get(noteId, USER_ID));
        return "main";
    }
}

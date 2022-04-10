package com.notepad.web.controller;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(required = false)Integer notebookId,
                         @RequestParam(required = false)Integer noteId) {

        List<Notebook> notebooks = notebookService.getAll();
        model.addAttribute("notebooks", notebooks);
        model.addAttribute("currentNotebook", notebookService.get(notebookId));
        model.addAttribute("notes", noteService.getAllByNotebookId(notebookId));
        model.addAttribute("currentNote", noteService.get(noteId));
        return "main";
    }

    @PostMapping(value = "notebook/create")
    public String create(@RequestParam String notebookName) {
        notebookService.create(notebookName);
        return "redirect:/";
    }

    @PostMapping(value = "notebook/rename")
    public String rename(@RequestParam Integer notebookId, @RequestParam String notebookNewName) {
        notebookService.rename(notebookId, notebookNewName);
        return "redirect:/";
    }

    @PostMapping(value = "/notebook/delete")
    public String delete(@RequestParam Integer notebookId) {
        notebookService.delete(notebookId);
        return "redirect:/";
    }
}

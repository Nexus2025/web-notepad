package com.notepad.web.controller;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/notebook")
public class UiNotebookController {

    @Autowired
    private NotebookService notebookService;

    private final static Integer USER_ID = 1;

    @PostMapping(value = "/create")
    public RedirectView create(@RequestParam String notebookName, RedirectAttributes attributes) {
        Notebook newNotebook = notebookService.create(notebookName, USER_ID);
        attributes.addAttribute("notebookId", newNotebook.getId());

        return new RedirectView("/");
    }

    @PostMapping(value = "/rename")
    public RedirectView rename(@RequestParam Integer notebookId, @RequestParam String notebookNewName,
                               RedirectAttributes attributes) {

        notebookService.rename(notebookId, notebookNewName, USER_ID);
        attributes.addAttribute("notebookId", notebookId);

        return new RedirectView("/");
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam Integer notebookId) {
        notebookService.delete(notebookId, USER_ID);
        return "redirect:/";
    }
}

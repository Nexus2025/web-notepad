package com.notepad.web.controller.ui;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NotebookService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/notebook")
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create")
    @Transactional
    public RedirectView create(@RequestParam String notebookName, RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        Notebook notebook = notebookService.create(notebookName, userId);
        attributes.addAttribute("notebookId", notebook.getId());

        return new RedirectView("/");
    }

    @PostMapping(value = "/rename")
    @Transactional
    public RedirectView rename(@RequestParam Integer notebookId, @RequestParam String notebookNewName,
                               RedirectAttributes attributes, Principal principal) {

        Integer userId = userService.findByUserName(principal.getName()).getId();
        Notebook notebook = notebookService.rename(notebookNewName, notebookId, userId);
        attributes.addAttribute("notebookId", notebook.getId());

        return new RedirectView("/");
    }

    @PostMapping(value = "/delete")
    @Transactional
    public String delete(@RequestParam Integer notebookId, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        notebookService.delete(notebookId, userId);
        return "redirect:/";
    }
}

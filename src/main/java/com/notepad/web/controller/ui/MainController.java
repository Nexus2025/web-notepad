package com.notepad.web.controller.ui;

import com.notepad.web.entity.Notebook;
import com.notepad.web.entity.Role;
import com.notepad.web.entity.User;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.NotebookService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Transactional
    public String getAll(Model model, Principal principal,
                         @RequestParam(required = false)Integer notebookId,
                         @RequestParam(required = false)Integer noteId) {

        User user = userService.findByUserName(principal.getName());
        Integer userId = user.getId();

        List<Notebook> notebooks = notebookService.getAll(userId);
        model.addAttribute("notebooks", notebooks);
        model.addAttribute("isAdmin", user.getRoles().stream().anyMatch(r -> r.equals(Role.ROLE_ADMIN)));

        if (notebookId != null) {
            model.addAttribute("currentNotebook", notebookService.get(notebookId, userId));
            model.addAttribute("notes", noteService.getAllByNotebookId(notebookId, userId));
        }

        if (noteId != null) {
            model.addAttribute("currentNote", noteService.get(noteId, userId));
        }
        return "main";
    }
}

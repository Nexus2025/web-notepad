package com.notepad.web.controller.rest;

import com.notepad.web.entity.Notebook;
import com.notepad.web.service.NotebookService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/notebooks", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotebookRestController {

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Notebook> getAll(Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        return notebookService.getAll(userId);
    }

    @GetMapping("/{id}")
    public Notebook get(@PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        return notebookService.get(id, userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Notebook notebook, @PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        if (notebook.getId().equals(id)) {
            notebookService.update(notebook, userId);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        notebookService.delete(id, userId);
    }
}

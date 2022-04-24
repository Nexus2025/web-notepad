package com.notepad.web.controller.rest;

import com.notepad.web.entity.Note;
import com.notepad.web.service.NoteService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping("/notebooks/{notebookId}/notes")
    public List<Note> getAll(Principal principal, @PathVariable Integer notebookId) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        return noteService.getAllByNotebookId(notebookId, userId);
    }

    @GetMapping("/notes/{id}")
    public Note get(@PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        return noteService.get(id, userId);
    }

    @PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> create(@RequestBody Note note, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        note.setUserId(userId);
        Note created = noteService.save(note);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/notes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/notes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Note note, @PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        if (note.getId().equals(id)) {
            noteService.update(note, userId);
        }
    }

    @DeleteMapping("/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Principal principal) {
        Integer userId = userService.findByUserName(principal.getName()).getId();
        noteService.delete(id, userId);
    }
}

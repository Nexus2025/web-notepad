package com.notepad.web.service;

import com.notepad.web.entity.Note;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();

    public NoteService() {
        notes.add(new Note(1, 1, 1, "Collections", "Collections Content", ZonedDateTime.now()));
        notes.add(new Note(2, 1, 1, "Multithreading", "Multithreading Content", ZonedDateTime.now()));
        notes.add(new Note(3, 1, 1, "Stream API", "Stream API Content", ZonedDateTime.now()));
        notes.add(new Note(4, 1, 1, "I/O", "I/O Content", ZonedDateTime.now()));
        notes.add(new Note(5, 1, 1, "Exceptions", "Exceptions Content", ZonedDateTime.now()));
        notes.add(new Note(6, 2, 1, "Spring MVC", "Spring MVC Content", ZonedDateTime.now()));
        notes.add(new Note(7, 2, 1, "Spring AOP", "Spring AOP Content", ZonedDateTime.now()));
        notes.add(new Note(8, 2, 1, "Spring Boot", "Spring Boot Content", ZonedDateTime.now()));

//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
//        notes.add(new Note(1, 1, 1, "NOTE", "Collections Content", ZonedDateTime.now()));
    }

    public Note get(final Integer id) {
        return id != null ? notes.stream().filter(n -> n.getId().equals(id)).findFirst().get() : null;
    }

    public List<Note> getAllByNotebookId(Integer id) {
        return id != null ? notes.stream().filter(n -> n.getNotebookId().equals(id)).collect(Collectors.toList()) : null;
    }
}

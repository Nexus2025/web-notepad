package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private static final List<Note> notes = new ArrayList<>();

    static {
        notes.add(new Note(1, 1, 1, "Collections", "Collections Content", ZonedDateTime.now()));
        notes.add(new Note(2, 1, 1, "Multithreading", "Multithreading Content", ZonedDateTime.now()));
        notes.add(new Note(3, 1, 1, "Stream API", "Stream API Content", ZonedDateTime.now()));
        notes.add(new Note(4, 1, 1, "I/O", "I/O Content", ZonedDateTime.now()));
        notes.add(new Note(5, 1, 1, "Exceptions", "Exceptions Content", ZonedDateTime.now()));
        notes.add(new Note(6, 2, 1, "Spring MVC", "Spring MVC Content", ZonedDateTime.now()));
        notes.add(new Note(7, 2, 1, "Spring AOP", "Spring AOP Content", ZonedDateTime.now()));
        notes.add(new Note(8, 2, 1, "Spring Boot", "Spring Boot Content", ZonedDateTime.now()));
    }

    public Note get(Integer id, Integer userId) {
        return id != null ? notes.stream().filter(n -> n.getId().equals(id)).findFirst().get() : null;
    }

    public List<Note> getAllByNotebookId(Integer id, Integer userId) {
        return id != null ? notes.stream().filter(n -> n.getNotebookId().equals(id)).collect(Collectors.toList()) : null;
    }

    public Note create(Integer notebookId, String name, Integer userId) {
        Note fromList = notes.stream().max(Comparator.comparingInt(Note::getId)).orElse(null);
        Integer id = fromList != null ? fromList.getId() + 1 : 1;

        Note note = new Note(id, notebookId, 1, name, "", getTime());
        notes.add(note);

        return note;
    }

    public Note rename(Integer id, String newName, Integer userId) {
        Note note = notes.stream().filter(n -> n.getId().equals(id)).findFirst().get();
        note.setName(newName);
        note.setLastModified(getTime());

        return note;
    }

    public void update(Integer id, String content, Integer userId) {
        Note note = notes.stream().filter(n -> n.getId().equals(id)).findFirst().get();
        note.setContent(content);
        note.setLastModified(getTime());
    }

    public void delete(Integer id, Integer userId) {
        notes.removeIf(n -> n.getId().equals(id));
    }

    private ZonedDateTime getTime() {
        return ZonedDateTime.now();
    }
}

package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note get(Integer id, Integer userId) {
        return noteRepository.getOne(id);
    }

    public List<Note> getAllByNotebookId(Integer id, Integer userId) {
        return noteRepository.findAllByNotebookId(id);
    }

    public Note create(Note note, Integer userId) {
        note.setLastModified(getTime());
        note.setContent("Default content");
        return noteRepository.save(note);
    }

    public Note rename(Integer id, String newName, Integer userId) {
        Note note = noteRepository.getOne(id);
        note.setName(newName);
        note.setLastModified(getTime());
        return noteRepository.save(note);
    }

    public void update(Integer id, String content, Integer userId) {
        Note note = noteRepository.getOne(id);
        note.setContent(content);
        note.setLastModified(getTime());
        noteRepository.save(note);
    }

    public void delete(Integer id, Integer userId) {
        noteRepository.deleteById(id);
    }

    private ZonedDateTime getTime() {
        return ZonedDateTime.now();
    }
}

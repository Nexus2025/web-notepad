package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import com.notepad.web.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private DateTimeService dateTimeService;

    public Note create(String noteName, Integer notebookId, Integer userId) {
        Note note = new Note(notebookId, userId, noteName);
        note.setLastModified(dateTimeService.getTime());
        note.setContent("Default content");
        return noteRepository.save(note);
    }

    public Note rename(Integer id, String newName, Integer userId) {
        noteRepository.rename(id, newName, userId);
        return noteRepository.get(id, userId);
    }

    public void updateContent(Integer id, String content, Integer userId) {
        noteRepository.updateContent(id, content, userId, dateTimeService.getTime());
    }

    public void delete(Integer id, Integer userId) {
        noteRepository.delete(id, userId);
    }

    public Note get(Integer id, Integer userId) {
        return noteRepository.get(id, userId);
    }

    public List<Note> getAllByNotebookId(Integer notebookId, Integer userId) {
        return noteRepository.getAllByNotebookId(notebookId, userId);
    }

    public void saveList(List<Note> notes) {
        noteRepository.saveAll(notes);
    }
}

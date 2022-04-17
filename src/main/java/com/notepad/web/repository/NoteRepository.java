package com.notepad.web.repository;

import com.notepad.web.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findAllByNotebookId(Integer id);
}
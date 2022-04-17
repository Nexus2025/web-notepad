package com.notepad.web.repository;

import com.notepad.web.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Integer> {
    List<Notebook> findAllByUserId(Integer userId);
}

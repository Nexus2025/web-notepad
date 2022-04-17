package com.notepad.web.service;

import com.notepad.web.entity.Notebook;
import com.notepad.web.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

    public Notebook create(Notebook notebook, Integer userId) {
        return notebookRepository.save(notebook);
    }

    public Notebook rename(Notebook notebook, Integer userId) {
        return notebookRepository.save(notebook);
    }

    public List<Notebook> getAll(Integer userId) {
        return notebookRepository.findAllByUserId(userId);
    }

    public Notebook get(Integer id, Integer userId) {
        return notebookRepository.getOne(id);
    }

    public void delete(Integer id, Integer userId) {
        notebookRepository.deleteById(id);
    }
}

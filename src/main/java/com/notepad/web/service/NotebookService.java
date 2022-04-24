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

    public Notebook create(String name, Integer userId) {
        return notebookRepository.save(new Notebook(userId, name));
    }

    public Notebook rename(String newName, Integer id, Integer userId) {
        notebookRepository.rename(newName, id, userId);
        return notebookRepository.get(id, userId);
    }

    public void update(Notebook notebook, Integer userId) {
        if (notebook.getUserId().equals(userId)) {
            notebookRepository.save(notebook);
        }
    }

    public Notebook save (Notebook notebook) {
        return notebookRepository.save(notebook);
    }

    public void delete(Integer id, Integer userId) {
        notebookRepository.delete(id, userId);
    }

    public Notebook get(Integer id, Integer userId) {
        return notebookRepository.get(id, userId);
    }

    public List<Notebook> getAll(Integer userId) {
        return notebookRepository.findAllByUserId(userId);
    }

    public void deleteAllNotebooksByUserId(Integer userId) {
        notebookRepository.deleteAllByUserId(userId);
    }

    public void saveList(List<Notebook> notebooks) {
        notebookRepository.saveAll(notebooks);
    }
}

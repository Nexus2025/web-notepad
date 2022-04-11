package com.notepad.web.service;

import com.notepad.web.entity.Notebook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class NotebookService {

    private static final List<Notebook> notebooks = new ArrayList<>();

    static {
        notebooks.add(new Notebook(1, 1, "Java"));
        notebooks.add(new Notebook(2, 1, "Spring"));
        notebooks.add(new Notebook(3, 1, "Computer Science"));
    }

    public Notebook create(String name, Integer userId) {
        Notebook fromList = notebooks.stream().max(Comparator.comparingInt(Notebook::getId)).orElse(null);
        Integer id = fromList != null ? fromList.getId() + 1 : 1;

        Notebook notebook = new Notebook(id, 1, name);
        notebooks.add(notebook);
        return notebook;
    }

    public Notebook rename(Integer id, String newName, Integer userId) {
        Notebook notebook = notebooks.stream().filter(n -> n.getId().equals(id)).findFirst().get();
        notebook.setName(newName);
        return notebook;
    }

    public List<Notebook> getAll(Integer userId) {
        return notebooks;
    }

    public Notebook get(Integer id, Integer userId) {
        return id != null ? notebooks.stream().filter(n -> n.getId().equals(id)).findFirst().get() : null;
    }

    public void delete(Integer id, Integer userId) {
        notebooks.removeIf(notebook -> notebook.getId().equals(id));
    }
}

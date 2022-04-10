package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotebookService {

    private final List<Notebook> notebooks = new ArrayList<>();

    public NotebookService(){
        notebooks.add(new Notebook(1, 1, "Java"));
        notebooks.add(new Notebook(2, 1, "Spring"));
        notebooks.add(new Notebook(3, 1, "Computer Science"));

//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
//        notebooks.add(new Notebook(3, 1, "NOTEBOOK"));
    }

    public List<Notebook> getAll() {
        return notebooks;
    }

    public Notebook get(Integer id) {
        return id != null ? notebooks.stream().filter(n -> n.getId().equals(id)).findFirst().get() : null;
    }

    public void delete(Integer id) {
        notebooks.removeIf(notebook -> notebook.getId().equals(id));
    }
}

package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import com.notepad.web.entity.User;
import com.notepad.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private DateTimeService dateTimeService;

    @Resource(name="demo-user-content")
    private Properties demoContentProperties;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(dateTimeService.getTime());

        return userRepository.save(user);
    }

    public void refreshDemoUserData() {
        User user = findByUserName("DemoUser");
        Integer userId = user.getId();

        notebookService.deleteAllNotebooksByUserId(userId);

        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook(userId, "Java[demo]"));
        notebooks.add(new Notebook(userId, "Spring[demo]"));
        notebooks.add(new Notebook(userId, "Database[demo]"));

        notebookService.saveList(notebooks);

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(notebooks.get(0).getId(), userId, "Collections[demo]", demoContentProperties.getProperty("content1"), dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "Multithreading[demo]", demoContentProperties.getProperty("content2"), dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "I/O[demo]", demoContentProperties.getProperty("content3"), dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "Stream API[demo]", demoContentProperties.getProperty("content4"), dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(1).getId(), userId, "Spring AOP[demo]", demoContentProperties.getProperty("content5"), dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(1).getId(), userId, "Spring MVC[demo]", demoContentProperties.getProperty("content6"), dateTimeService.getTime()));

        noteService.saveList(notes);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}

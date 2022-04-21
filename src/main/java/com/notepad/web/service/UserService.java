package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import com.notepad.web.entity.Role;
import com.notepad.web.entity.User;
import com.notepad.web.repository.RoleRepository;
import com.notepad.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private DateTimeService dateTimeService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(2));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public void refreshDemoUserData() {
        User user = findByUserName("DemoUser");
        Integer userId = user.getId();

        notebookService.deleteAllNotebooksByUserId(userId);

        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook(userId, "Java [demo]"));
        notebooks.add(new Notebook(userId, "Spring [demo]"));
        notebooks.add(new Notebook(userId, "Database [demo]"));

        notebookService.saveList(notebooks);

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(notebooks.get(0).getId(), userId, "Collections [demo]", "Collections Note. Demo Content", dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "Multithreading [demo]", "Multithreading Note. Demo Content", dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "I/O [demo]", "I/O Note. Demo Content", dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(0).getId(), userId, "Stream API [demo]", "Stream API Note. Demo Content", dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(1).getId(), userId, "Spring AOP [demo]", "Spring AOP Note. Demo Content", dateTimeService.getTime()));
        notes.add(new Note(notebooks.get(1).getId(), userId, "Spring MVC [demo]", "Spring MVC Note. Demo Content", dateTimeService.getTime()));

        noteService.saveList(notes);
    }
}

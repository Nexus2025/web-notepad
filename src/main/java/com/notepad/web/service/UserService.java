package com.notepad.web.service;

import com.notepad.web.entity.Note;
import com.notepad.web.entity.Notebook;
import com.notepad.web.entity.Role;
import com.notepad.web.entity.User;
import com.notepad.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

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


    public User get(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(dateTimeService.getTime());

        return userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
    }
}

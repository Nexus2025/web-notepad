package com.notepad.web.controller;

import com.notepad.web.entity.User;
import com.notepad.web.service.RoleService;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashSet;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());

        return "admin";
    }

    @PostMapping("/user/create")
    @Transactional
    public String create(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (userService.findByUserName(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRoles(new HashSet<>(Collections.singletonList(roleService.getByName(role))));
            userService.save(user);
        }

        return "redirect:/admin";
    }

    @PostMapping("/user/delete")
    public String delete(@RequestParam Integer userId) {
        userService.delete(userId);

        return "redirect:/admin";
    }
}

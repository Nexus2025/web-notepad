package com.notepad.web.controller.ui;

import com.notepad.web.entity.Role;
import com.notepad.web.entity.User;
import com.notepad.web.service.SecurityService;
import com.notepad.web.service.UserService;
import com.notepad.web.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;

@Controller
public class AuthController {

    private static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @PostMapping(value = "/register")
    public String registration(@ModelAttribute("userForm") User userForm,
                               @RequestParam String confirmPassword,
                               BindingResult bindingResult) {
        userValidator.validateRegistration(userForm, bindingResult, confirmPassword);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userForm.setRoles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)));

        User user = userService.save(userForm);
        securityService.autoLogin(user.getUsername(), confirmPassword);

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("userForm", new User());
        return "login";
    }


    @PostMapping(value = "/login")
    public String login(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

        userValidator.validateLogin(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.findByUserName(userForm.getUsername());

        if (user != null && bCryptPasswordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            securityService.autoLogin(userForm.getUsername(), userForm.getPassword());
            return "redirect:/";
        }

        return "login";
    }

    @GetMapping(value = "/login/demo")
    @Transactional
    public String loginDemo() {
        securityService.autoLogin("DemoUser", "12345");
        userService.refreshDemoUserData();

        return "redirect:/";
    }
}

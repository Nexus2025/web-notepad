package com.notepad.web.controller;

import com.notepad.web.entity.User;
import com.notepad.web.service.SecurityService;
import com.notepad.web.service.UserService;
import com.notepad.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

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
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validateRegistration(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = userService.save(userForm);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());

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
    public String loginDemo() {
        //TO DO
        return "redirect:/";
    }
}

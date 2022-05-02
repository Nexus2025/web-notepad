package com.notepad.web.validation;

import com.notepad.web.entity.User;
import com.notepad.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class UserValidator {

    @Autowired
    private UserService userService;

    public void validateRegistration(User user, Errors errors, String confirmPassword) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 5) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUserName(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 5) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!confirmPassword.equals(user.getPassword())) {
            errors.rejectValue("password", "Different.userForm.password");
        }
    }

    public void validateLogin(User user, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 5) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUserName(user.getUsername()) == null) {
            errors.rejectValue("username", "NotExist.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 5) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}

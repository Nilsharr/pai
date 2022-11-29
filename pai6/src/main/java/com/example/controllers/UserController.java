package com.example.controllers;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao dao;

    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        m.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        if (dao.findByLogin(user.getLogin()) != null) {
            result.addError(new FieldError("user", "login", "Given login is taken"));
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfilePage(Model m, Principal principal) {
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "editProfile";
    }

    @PostMapping("/editProfile")
    public String editProfilePagePOST(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "editProfile";
        }
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        User current = dao.findById(user.getUserid()).get();
        if (!current.getLogin().equals(user.getLogin()) && dao.findByLogin(user.getLogin()) != null) {
            result.addError(new FieldError("user", "login", "Given login is taken"));
            return "editProfile";
        }
        if (!user.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(current.getPassword());
        }
        dao.save(user);
        return "profile";
    }

    @GetMapping("/deleteProfile")
    public String deleteProfile(Principal principal) {
        dao.delete(dao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }

    @GetMapping("/users")
    public String getUsers(Model m) {
        m.addAttribute("users", dao.findAll());
        return "users";
    }
}

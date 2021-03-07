package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/con")
public class Controller2 {
    private final UserDAO userDAO;

    @Autowired
    public Controller2(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    PasswordEncoder passwordEncoder;

    @Autowired
    public void PasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = {"", "/", "home"})
    public String home() {
        return "index";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @GetMapping("/arrivals")
    public String arrivals() {
        return "arrivals";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }

    @GetMapping("/editUser")
    public String editUserAdmin() {
        return "editUserAdmin";
    }

    @GetMapping("/editMyself")
    public String editUser() {
        return "editMyself";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam("username") String email, Model model) {
        System.out.println("REGISTRATION:" + email);

        if (userDAO.findByUserName(email) != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        } else {
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.addUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/test")
    public String test(Model model, Principal principal) {
        model.addAttribute("info", principal.getName());
        return "test";
    }
}
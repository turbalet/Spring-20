package kz.edu.controller;


import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import kz.edu.service.QuestionService;
import kz.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserDAO userDAO;

    private final UserService userService;

    private final QuestionService questionService;


    public SettingsController(UserDAO userDAO, UserService userService, QuestionService questionService) {
        this.userDAO = userDAO;
        this.userService = userService;
        this.questionService = questionService;
    }


    @GetMapping("")
    public String loadPage(Model model, Principal principal) {
        User user = userDAO.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("questions", questionService.getAll());
        return "test";
    }

    @PostMapping("/changePass")
    public String changePassword(Model model, Principal principal,
                                 @RequestParam("oldPass") String old,
                                 @RequestParam("newPass") String newPass,
                                 @RequestParam("reNew") String reNew) {
        User user = userDAO.findByUserName(principal.getName());
        String res = userService.changePassword(user, old, newPass, reNew);
        model.addAttribute("user", user);
        model.addAttribute("message", res);
        return "settings";
    }


}

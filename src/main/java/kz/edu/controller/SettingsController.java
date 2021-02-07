package kz.edu.controller;


import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import kz.edu.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private final UserDAO userDAO;


    public SettingsController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @GetMapping("")
    public String loadPage(Model model, Principal principal){
        User user = userDAO.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "settings";
    }


}

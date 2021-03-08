package kz.edu.controller;

import kz.edu.dao.UserRepository;
import kz.edu.model.User;
import kz.edu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    @GetMapping
    public String home(Principal principal, Model model ){
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "index";
    }


}

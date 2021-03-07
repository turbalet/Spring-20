package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.dao.UserRepository;
import kz.edu.dao.VoteRepository;
import kz.edu.model.User;
import kz.edu.model.Vote;
import kz.edu.service.UserService;
import kz.edu.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    private final VoteService voteService;

    private final UserDAO userDAO;

    @GetMapping
    public String profile(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        List<Vote> votes = voteService.getAllByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("votes", votes);
        return "profile";
    }

    @GetMapping("/edit")
    public String loadEditProfile(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "editMyself";
    }




}

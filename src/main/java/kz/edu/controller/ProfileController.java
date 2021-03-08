package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.dao.UserRepository;
import kz.edu.dao.VoteRepository;
import kz.edu.model.User;
import kz.edu.model.Vote;
import kz.edu.service.GroupService;
import kz.edu.service.UserService;
import kz.edu.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserService userService;

    private final VoteService voteService;

    private final GroupService groupService;

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


    @PostMapping("/change-password")
    public String changePassword(Principal principal, Model model,
                                 @RequestParam("oldPass") String old,
                                 @RequestParam("newPass") String newPass,
                                 @RequestParam("reNew") String reNew){
        User user = userDAO.findByUserName(principal.getName());
        String res = userService.changePassword(user, old, newPass, reNew);
        model.addAttribute("user", user);
        model.addAttribute("message", res);
        return profile(principal, model);
    }

    @GetMapping("/change-password")
    public String loadChangePassword(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "changePass";
    }


    @PostMapping("/edit")
    public String editUser(
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("group") String groupName,
            Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        try{
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthday(date);
            user.setGroup(groupService.getByName(groupName));
            userService.update(user);
            model.addAttribute("message", "Profile was successfully edited");
        } catch (Exception e){
            model.addAttribute("message", "Couldn't edit profile");
        }
        return profile(principal, model);
    }

}

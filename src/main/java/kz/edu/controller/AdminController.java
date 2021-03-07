package kz.edu.controller;

import kz.edu.model.*;
import kz.edu.service.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    private final AnswerService answerService;

    private final GroupService groupService;

    private final QuestionService questionService;

    private final VoteService voteService;

    @GetMapping
    public String adminPage(Principal principal, Model model){
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("questions", questionService.getAll());
        return "admin";
    }

    @GetMapping("/user/edit/{id}")
    public String loadEditUser(Principal principal, Model model, @PathVariable("id") long id){
        User edit = userService.getById(id);
        User user = userService.getUserByEmail(principal.getName());
        List<Role> roles = roleService.getAll();
        model.addAttribute("user", user);
        model.addAttribute("edit", edit);
        model.addAttribute("roles", roles);
        return "editUserAdmin";
    }

    @GetMapping("/question/edit/{id}")
    public String loadEditQuestion(Principal principal, Model model, @PathVariable("id") long id) {
        User user = userService.getUserByEmail(principal.getName());
        Question question = questionService.getById(id);
        if(question != null) {
            List<Answer> answers = answerService.getAllByQuestionId(question.getId());
            List<Vote> votes = voteService.getAllByQuestionId(id);
            model.addAttribute("answers", answers);
            model.addAttribute("votes", votes);
        }
        model.addAttribute("user", user);
        model.addAttribute("question", question);
        return "edit";
    }

    @PostMapping("/user/edit")
    public String editUser(
            @RequestParam("user_id") long id,
            @RequestParam("email") String email,
            @RequestParam("role") String role,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam("group") String groupName,
            Principal principal, Model model){
        User user = userService.getById(id);
        model.addAttribute("message", "");
        try{
            user.setEmail(email);
            user.setRole(roleService.getByName(role));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setBirthday(date);
            user.setGroup(groupService.getByName(groupName));
            userService.update(user);
        } catch (Exception e){
            model.addAttribute("message", "Couldn't edit user");
        }
        return loadEditUser(principal, model, id);
    }

    @PostMapping("/question/edit")
    @Transactional
    public String editQuestion(@RequestParam("id") String id,
                               @RequestParam("title") String title,
                               @RequestParam("user") String user,
                               @RequestParam("published") @DateTimeFormat(pattern = "yyyy-MM-dd") Date published,
                               @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                               @RequestParam("description") String description,
                               @RequestParam("answer") String[] answers,
                                Principal principal, Model model){
        System.out.println("SSS");
        Question question = new Question(Long.parseLong(id), userService.getById(Long.parseLong(user)), title, published, endDate, description);
        answerService.deleteByQuestionID(Long.parseLong(id));
        for(String answer : answers){
            answerService.add(new Answer(question, answer));
        }
        questionService.update(question);
        return loadEditQuestion(principal, model, Long.parseLong(id));
    }
}
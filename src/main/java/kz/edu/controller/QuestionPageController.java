package kz.edu.controller;

import kz.edu.model.*;
import kz.edu.service.AnswerService;
import kz.edu.service.QuestionService;
import kz.edu.service.UserService;
import kz.edu.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionPageController {

    private final QuestionService questionService;

    private final UserService userService;

    private final VoteService voteService;

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public String loadQuestion(@PathVariable("id") long id, Principal principal, Model model){
        try{
            Question question = questionService.getById(id);
            User user = userService.getUserByEmail(principal.getName());
            if(question!=null) {
                List<Answer> answers = answerService.getAllByQuestionId(id);
                List<Vote> votes = voteService.getAllByQuestionId(id);
                model.addAttribute("question", question);
                model.addAttribute("user", user);
                model.addAttribute("answers", answers);
                model.addAttribute("count", votes.size());
                model.addAttribute("groupVotes", voteService.getVotedGroupmates(user.getGroup(),question.getId(), user.getId()));
                boolean isVoted = voteService.isVoted(question.getId(), user.getId());
                if(isVoted){
                    Vote vote = voteService.getUserVote(question.getId(), user.getId());
                    model.addAttribute("userVote", vote);
                    Map<Answer, Integer> ans = new HashMap<>();
                    for(Answer answer: answers){
                        ans.put(answer, voteService.getVoteCountByAnswer(answer.getId()));
                    }
                    Statistic statistic = new Statistic(question, ans, votes.size());
                    model.addAttribute("statistic", statistic);
                }else {
                    model.addAttribute("userVote", new Vote());
                }
                model.addAttribute("isVoted", isVoted);
            }
        } catch (Exception e){

        }
        return "question";
    }

    @GetMapping("/vote/delete/{id}")
    @Transactional
    public String removeVote(@PathVariable("id") long id, Principal principal, Model model){
        try{
            Vote vote = voteService.getById(id);
            User user = userService.getUserByEmail(principal.getName());
            if(vote!=null){
                voteService.delete(id, user.getId());
                model.addAttribute("message", "Your vote was removed");
                return loadQuestion(vote.getQuestion().getId(), principal, model);
            }
        } catch (Exception e){
            model.addAttribute("message", "Couldn't delete vote");
        }
        return "question";
    }


    @PostMapping("/vote")
    public String vote(@RequestParam("answer") String answer, @RequestParam("questionId") long questionId, Principal principal, Model model){
        try{
            Question question = questionService.getById(questionId);
            Answer ans = answerService.getById(Long.parseLong(answer));
            User user = userService.getUserByEmail(principal.getName());
            Vote vote = new Vote(question,ans,user,new Date(), new Date());
            voteService.add(vote);
        }
        catch (Exception e){
            model.addAttribute("message", "Couldn't process your vote");
        }
        return loadQuestion(questionId, principal, model);
    }


}

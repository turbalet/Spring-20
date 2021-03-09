package kz.edu.controller;

import kz.edu.dao.UserRepository;
import kz.edu.mapreduce.MapReduce;
import kz.edu.model.User;
import kz.edu.service.QuestionService;
import kz.edu.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;

    private final QuestionService questionService;

    private final MapReduce mapReduce;

    @GetMapping
    public String home(Principal principal, Model model ){
        mapReduce.voteCount.clear();
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("outdated", questionService.getAllActual());
        model.addAttribute("actual", questionService.getAllOutdated());
        model.addAttribute("map", voteCount());
        return "index";
    }


    public Map<Long, Integer> voteCount(){
        try {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    try {
                        mapReduce.produce();
                    }
                    catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run()
                {
                    try {
                        mapReduce.consume();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            for (long key : mapReduce.voteCount.keySet()){
                System.out.println("Question " + key + " " + mapReduce.voteCount.get(key));
            }
        } catch (Exception e){
            System.out.println(e);
            System.out.println("error");
        }
        return mapReduce.voteCount;
    }

}

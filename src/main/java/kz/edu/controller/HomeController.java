package kz.edu.controller;

import kz.edu.dao.AccountRepository;
import kz.edu.dao.UserDAO;
import kz.edu.model.Favourite;
import kz.edu.model.User;
import kz.edu.service.FavouriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final AccountRepository accountRepository;

    private final UserDAO userDAO;

    private final FavouriteService favouriteService;

    public HomeController(AccountRepository accountRepository, UserDAO userDAO, FavouriteService favouriteService) {
        this.accountRepository = accountRepository;
        this.userDAO = userDAO;
        this.favouriteService = favouriteService;
    }


    @GetMapping("")
    public String home(Model model, Principal principal){
        User user = userDAO.findByUserName(principal.getName());
        List<Favourite> favourites = favouriteService.getFavouritesByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("favourites", favourites);
        model.addAttribute("accounts", accountRepository.findAccountsByUserId(user.getId()));
        return "/home";
    }
}

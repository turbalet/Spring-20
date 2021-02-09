package kz.edu.controller;

import kz.edu.dao.UserDAO;
import kz.edu.model.Account;
import kz.edu.model.Favourite;
import kz.edu.model.User;
import kz.edu.service.AccountService;
import kz.edu.service.FavouriteService;
import kz.edu.service.TransactionService;
import kz.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final UserDAO userDAO;

    private final AccountService accountService;

    private final UserService userService;

    private final TransactionService transactionService;

    private final FavouriteService favouriteService;

    public TransferController(UserDAO userDAO, AccountService accountService, UserService userService, TransactionService transactionService, FavouriteService favouriteService) {
        this.userDAO = userDAO;
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.favouriteService = favouriteService;
    }


    @GetMapping("")
    public String loadForm(Model model, Principal principal){
        User user = userDAO.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("accounts", accountService.getAccounts(user.getId()));
        return "transfer";
    }

    @GetMapping("/{id}")
    public String loadFavourite(@PathVariable("id") String id, Model model, Principal principal){
        User user = userDAO.findByUserName(principal.getName());
        Favourite favourite = favouriteService.getById(Integer.parseInt(id));
        model.addAttribute("favourite", favourite);
        model.addAttribute("user", user);
        model.addAttribute("accounts", accountService.getAccounts(user.getId()));
        return "transfer";
    }

    @PostMapping("")
    public String transfer(@RequestParam( value =  "sender") String id,
                           @RequestParam("accountNumber") String receiverNum,
                           @RequestParam("amount") String amount,
                           @RequestParam("type") String type,
                           @RequestParam(value = "favourite", required = false) String favourite,
                           Model model, Principal principal) throws Exception {
        User user  = userService.getUserByEmail(principal.getName());
        Account sender = accountService.getAccountDetails(user, Integer.parseInt(id));
        Account receiver = accountService.getAccountByNum(receiverNum);
        transactionService.transfer(sender, receiver, Double.parseDouble(amount), type);
        if(favourite!=null){
            favouriteService.addUserFav(user,receiverNum);
        }
        model.addAttribute("message", "Successfully");
        return loadForm(model, principal);
    }

}

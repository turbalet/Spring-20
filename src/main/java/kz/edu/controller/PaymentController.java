package kz.edu.controller;


import kz.edu.dao.UserDAO;
import kz.edu.model.Account;
import kz.edu.model.User;
import kz.edu.service.AccountService;
import kz.edu.service.FavouriteService;
import kz.edu.service.TransactionService;
import kz.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final UserDAO userDAO;

    private final AccountService accountService;

    private final UserService userService;

    private final TransactionService transactionService;

    private final FavouriteService favouriteService;



    public PaymentController(UserDAO userDAO, AccountService accountService, UserService userService, TransactionService transactionService, FavouriteService favouriteService) {
        this.userDAO = userDAO;
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.favouriteService = favouriteService;
    }

    @GetMapping("")
    public String loadPayments(Model model, Principal principal){
        User user = userDAO.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("accounts", accountService.getAccounts(user.getId()));
        return "payment";
    }


    @PostMapping("/mobile")
    public String processMobile(@RequestParam("sender") String id,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("amount") String amount,
            Model model, Principal principal) throws Exception {
        User user  = userService.getUserByEmail(principal.getName());
        Account sender = accountService.getAccountDetails(user, Integer.parseInt(id));
        transactionService.phonePayment(sender, phoneNumber, Double.parseDouble(amount));
        return loadPayments(model, principal);
    }


    @PostMapping("/house")
    public String processHouse(
            @RequestParam("sender") String id,
            @RequestParam("houseNumber") String houseAccount,
            @RequestParam("address") String address,
            @RequestParam("amount") String amount,
            Model model, Principal principal) throws Exception {
        User user  = userService.getUserByEmail(principal.getName());
        Account sender = accountService.getAccountDetails(user, Integer.parseInt(id));
        transactionService.housePayment(sender,houseAccount, address , Double.parseDouble(amount));
        return loadPayments(model, principal);
    }



}

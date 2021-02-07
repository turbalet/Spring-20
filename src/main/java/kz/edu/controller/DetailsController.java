package kz.edu.controller;

import kz.edu.model.Account;
import kz.edu.model.TransactionHistory;
import kz.edu.model.User;
import kz.edu.service.AccountService;
import kz.edu.service.TransactionHistoryService;
import kz.edu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/details")
public class DetailsController {

    private final AccountService accountService;

    private final UserService userService;

    private final TransactionHistoryService transactionHistoryService;


    public DetailsController(AccountService accountService, UserService userService, TransactionHistoryService transactionHistoryService) {
        this.accountService = accountService;
        this.userService = userService;
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping("/card/{id}")
    public String getCardDetails(@PathVariable("id") int id, Model model, Principal principal){
        User user  = userService.getUserByEmail(principal.getName());
        Account account = accountService.getAccountDetails(user, id);
        if(account!=null){
            List<TransactionHistory> incomes = transactionHistoryService.getLatestIncomes(account.getAccountId());
            System.out.println(incomes);
            List<TransactionHistory> outcomes = transactionHistoryService.getLatestOutcomes(account.getAccountId());
            List<TransactionHistory> transactions = transactionHistoryService.getAll(account.getAccountId());
            System.out.println(transactions);
            double incomeSum = transactionHistoryService.getSum(incomes);
            double outcomeSum = transactionHistoryService.getSum(outcomes);
            model.addAttribute("transactions", transactions);
            model.addAttribute("account", account);
            model.addAttribute("outcomes", outcomes);
            model.addAttribute("incomeSum", incomeSum);
            model.addAttribute("outcomeSum", outcomeSum);
            return "card";
        }
        model.addAttribute("message", "Couldn't access to the card");
        return "home";
    }

}

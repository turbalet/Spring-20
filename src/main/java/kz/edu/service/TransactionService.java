package kz.edu.service;

import kz.edu.model.Account;
import kz.edu.model.TransactionHistory;
import kz.edu.model.TransactionType;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionService {

    private final TransactionHistoryService transactionHistoryService;

    private final AccountService accountService;

    private final TransactionTypeService transactionTypeService;

    public TransactionService(TransactionHistoryService transactionHistoryService, AccountService accountService, TransactionTypeService transactionTypeService) {
        this.transactionHistoryService = transactionHistoryService;
        this.accountService = accountService;
        this.transactionTypeService = transactionTypeService;
    }

    public void transfer(Account sender, Account receiver, double amount, String type) throws Exception{
        try {
            if (sender.getBalance()-amount >= 0) {
                switch (type) {
                    case "between accounts":
                        betweenAccounts(sender, receiver, amount, transactionTypeService.getTransactionTypeByName(type));
                        break;
                    case "between bank":
                        betweenBanks(sender, receiver, amount, transactionTypeService.getTransactionTypeByName(type));
                        break;
                    case "another card":
                        anotherCard(sender, receiver, amount, transactionTypeService.getTransactionTypeByName(type));
                        break;
                }
            } else {
                throw new Exception();
            }

        } catch (Exception e){
            throw new Exception("Invalid Transfer");
        }
    }

    public void phonePayment(Account sender, String phoneNumber, double amount) throws Exception {
        try {
            if (sender.getBalance() - amount >= 0) {
                sender.setBalance(sender.getBalance() - amount);
                accountService.update(sender);
                transactionHistoryService.save(new TransactionHistory(new Date(), sender.getAccountId(), 9, amount, " ", transactionTypeService.getTransactionTypeByName("payment")));
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw  new Exception("Invalid Payment");
        }

    }

    public void housePayment(Account sender, String houseAccount,String address, double amount) throws Exception {
        try {
            if (sender.getBalance() - amount >= 0) {
                sender.setBalance(sender.getBalance() - amount);
                accountService.update(sender);
                transactionHistoryService.save(new TransactionHistory(new Date(), sender.getAccountId(), 9, amount, " ", transactionTypeService.getTransactionTypeByName("payment")));
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw  new Exception("Invalid Payment");
        }

    }


    public void betweenAccounts(Account sender, Account receiver, double amount, TransactionType type) throws Exception{
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        accountService.update(sender);
        accountService.update(receiver);
        transactionHistoryService.save(new TransactionHistory(new Date(), sender.getAccountId(), receiver.getAccountId(), amount, " ", type));
    }

    public void betweenBanks(Account sender, Account receiver, double amount, TransactionType type) throws Exception {
        sender.setBalance(sender.getBalance() - amount - (type.getFee() / 100.00) * (sender.getBalance()));
        accountService.update(sender);
        transactionHistoryService.save(new TransactionHistory(new Date(), sender.getAccountId(), receiver.getAccountId(), amount, " ", type));
    }

    public void anotherCard(Account sender, Account receiver, double amount, TransactionType type) throws Exception {
        double fee = 0;
        if(amount > 100000) fee = 1;
        sender.setBalance(sender.getBalance() - amount - (fee / 100.00) * (sender.getBalance()));
        receiver.setBalance(receiver.getBalance() + amount);
        accountService.update(sender);
        accountService.update(receiver);
        transactionHistoryService.save(new TransactionHistory(new Date(), sender.getAccountId(), receiver.getAccountId(), amount, " ", type));
    }

}

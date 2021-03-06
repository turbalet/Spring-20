package kz.edu.service;

import kz.edu.dao.AccountRepository;
import kz.edu.model.Account;
import kz.edu.model.User;
import kz.edu.service.interfaces.IAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountDetails(User user, int id) {
        Account account =  accountRepository.getOne(id);
        if(account.getUser().getId() == user.getId()){
            return account;
        }
        return null;
    }

    public Account getAccountByNum(String number){
        return accountRepository.findByAccountNumber(number);
    }

    public List<Account> getAccounts(long id){
        return accountRepository.findAccountsByUserId(id);
    }

    public void update(Account account){
        accountRepository.save(account);
    }
}

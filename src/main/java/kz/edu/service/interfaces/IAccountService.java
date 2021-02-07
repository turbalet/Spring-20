package kz.edu.service.interfaces;

import kz.edu.model.Account;
import kz.edu.model.User;

public interface IAccountService {
    Account getAccountDetails(User user, int id);
}

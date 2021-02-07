package kz.edu.service;

import kz.edu.dao.UserDAO;
import kz.edu.model.User;
import kz.edu.service.interfaces.IUserService;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByUserName(email);
    }
}

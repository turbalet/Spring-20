package kz.edu.service.interfaces;

import kz.edu.model.User;

public interface IUserService {
    User getUserByEmail(String email);
}

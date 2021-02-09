package kz.edu.service;

import kz.edu.dao.UserDAO;
import kz.edu.dao.UserRepository;
import kz.edu.model.User;
import kz.edu.service.interfaces.IUserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final UserDAO userDAO;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByUserName(email);
    }

    public String changePassword(User user, String oldPass, String newPass, String reNew){
        if(!newPass.equals(reNew)) return "New passwords don't match!";
        if(!passwordEncoder.matches(oldPass, user.getPassword())) return "Incorrect old password!";
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
        return "Successfully changed";
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(long id) {
        return userRepository.getOne(id);
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}

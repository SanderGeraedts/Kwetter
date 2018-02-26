package service;

import dao.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public void save(User user) {
        userDAO.save(user);
    }
}

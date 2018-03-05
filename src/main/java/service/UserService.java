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

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> findUserFollows(Long id) {
        User user = userDAO.findById(id);

        return user.getFollowing();
    }

    public User saveUserFollows(Long userId, Long followId) {
        User user = userDAO.findById(userId);
        User follow = userDAO.findById(followId);

        user.addFollowing(follow);

        userDAO.save(user);

        return user;
    }
}

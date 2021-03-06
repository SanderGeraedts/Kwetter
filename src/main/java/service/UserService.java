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

    public User create(String name, String email, String passwordHash, String description, String avatar) {
        if ("".equals(name) || "".equals(email) || "".equals(passwordHash)) {
            throw new IllegalArgumentException("Name, email and passwordHash are required fields and can't be null");
        }

        User user = new User(name, email, passwordHash, description, avatar);

        return userDAO.save(user);
    }

    public void update(User user) {
        userDAO.save(user);
    }

    public void remove(Long id) {
        User user = this.findById(id);

        userDAO.remove(user);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public List<User> findUserFollows(Long id) {
        User user = userDAO.findById(id);

        return user.getFollowing();
    }

    public List<User> findUserFollowers(Long id) {
        User user = userDAO.findById(id);

        return user.getFollowers();
    }

    public User findByCredentials(String email, String password) {
        return userDAO.findByCredentials(email, password);
    }

    public List<User> saveUserFollows(Long userId, Long followId) {
        User user = userDAO.findById(userId);
        User follow = userDAO.findById(followId);

        user.addFollowing(follow);

        userDAO.save(user);

        return user.getFollowing();
    }
}

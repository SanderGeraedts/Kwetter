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

    public void create(User user) {
        userDAO.save(user);
    }



    public User create(String name, String email, String passwordHash, String description, String avatar) {
        User user = new User(name, email, passwordHash, description, avatar);

        return userDAO.save(user);
    }

    public void update(User user) {
        userDAO.save(user);
    }

    public void remove(Long id) {
        userDAO.remove(this.findById(id));
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

    public List<User> saveUserFollows(Long userId, Long followId) {
        User user = userDAO.findById(userId);
        User follow = userDAO.findById(followId);

        user.addFollowing(follow);

        userDAO.save(user);

        return user.getFollowing();
    }
}

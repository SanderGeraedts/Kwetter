package service;

import dao.KweetDAO;
import dao.UserDAO;
import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class KweetService {

    @Inject
    KweetDAO kweetDAO;

    @Inject
    UserDAO userDAO;

    public Kweet find(Long id) {
        return kweetDAO.find(id);
    }

    public List<Kweet> findAll() {
        return kweetDAO.findAll();
    }

    public List<Kweet> findAllByUserId(Long id) {
        return kweetDAO.findAllByUserId(id);
    }

    public Integer heartKweet(Kweet kweet, Long userId) {
        User user = userDAO.findById(userId);

        kweetDAO.heartKweet(kweet, user);

        return kweet.getNumberOfHearts();
    }

    public Integer unheartKweet(Kweet kweet, Long userId) {
        User user = userDAO.findById(userId);

        kweetDAO.unheartKweet(kweet, user);

        return kweet.getNumberOfHearts();
    }

    public Kweet create(String message, Long userId) {
        User user = userDAO.findById(userId);

        Kweet kweet = new Kweet(message, user);

        return kweetDAO.save(kweet);
    }

    public Kweet update(Long id, String message) {
        Kweet kweet = kweetDAO.find(id);

        kweet.setMessage(message);

        return kweetDAO.save(kweet);
    }

    public void remove(Long id) {
        Kweet kweet = kweetDAO.find(id);

        kweetDAO.remove(kweet);
    }

}

package service;

import dao.NotificationDAO;
import dao.UserDAO;
import domain.Notification;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NotificationService {

    @Inject
    NotificationDAO notificationDAO;

    @Inject
    UserDAO userDAO;

    public List<Notification> findAllByUserId(Long userId) {
        User user = userDAO.findById(userId);

        return notificationDAO.findAllForUser(user);
    }

    public List<Notification> findAllUnreadByUserId(Long userId) {
        User user = userDAO.findById(userId);

        return notificationDAO.findAllUnreadForUser(user);
    }

    public Notification create(String message, Long userId) {
        User user = userDAO.findById(userId);

        Notification notification = new Notification(message, user);

        return notificationDAO.save(notification);
    }

    public void read(Long id) {
        Notification notification = notificationDAO.find(id);

        notificationDAO.read(notification);
    }

    public void remove(Long id) {
        Notification notification = notificationDAO.find(id);

        notificationDAO.remove(notification);
    }
}

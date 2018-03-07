package dao;

import domain.Notification;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class NotificationDAO {

    @PersistenceContext
    EntityManager em;

    public NotificationDAO() {
    }

    public Notification find(Long id) {
        return em.find(Notification.class, id);
    }

    public List<Notification> findAllForUser(User user) {
        return em.createNamedQuery("Notification.allNotificationsFromUser")
                .setParameter("id", user)
                .getResultList();
    }
    public List<Notification> findAllUnreadForUser(User user) {
        return em.createNamedQuery("Notification.allUnreadNotificationsFromUser")
                .setParameter("id", user)
                .getResultList();
    }

    public Notification save(Notification notification) {
        em.persist(notification);

        return notification;
    }

    public void read(Notification notification) {
        notification.setRead(true);

        em.persist(notification);
    }

    public void remove(Notification notification) {
        em.remove(notification);
    }

}

package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext
    EntityManager em;


    public UserDAO() {}

    public User save(User user) {
        em.persist(user);

        return user;
    }

    public void update(User user) {
        em.persist(user);
    }

    public void remove(User user) {
        em.remove(user);
    }

    public List<User> findAll() {
        return em.createNamedQuery("User.allUser").getResultList();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

}

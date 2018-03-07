package dao;

import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//Todo: Fix JPA
@Stateless
public class KweetDAO {

    @PersistenceContext
    EntityManager em;

    public KweetDAO() { }

    public List<Kweet> findAll() {
        return em.createNamedQuery("Kweet.allKweets").getResultList();
    }

    public void heartKweet(Kweet kweet, User user) {
        kweet.addHeart(user);

        em.persist(kweet);
    }

    public void save(Kweet kweet) {
        em.persist(kweet);
    }
}

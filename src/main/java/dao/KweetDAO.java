package dao;

import domain.Kweet;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class KweetDAO {

    @PersistenceContext
    EntityManager em;

    public KweetDAO() { }

    public Kweet find(Long id) {
        return em.find(Kweet.class, id);
    }

    public List<Kweet> findAll() {
        return em.createNamedQuery("Kweet.allKweets").getResultList();
    }

    public List<Kweet> findAllByUserId(Long id) {
        return em.createNamedQuery("Kweet.allKweetsFromUser")
                .setParameter("id", id)
                .getResultList();
    }

    public void heartKweet(Kweet kweet, User user) {
        kweet.addHeart(user);

        em.persist(kweet);
    }

    public void unheartKweet(Kweet kweet, User user) {
        kweet.removeHeart(user);

        em.persist(kweet);
    }

    public Kweet save(Kweet kweet) {
        em.persist(kweet);

        return kweet;
    }

    public void remove(Kweet kweet) {
        em.remove(kweet);
    }
}

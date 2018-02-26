package dao;

import domain.User;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

//Todo: Fix JPA
@Singleton
@Startup
public class UserDAO {

    @PersistenceContext()
    EntityManager em;

    private List<User> users;

    public UserDAO() {
        User user1 = new User("Sander Geraedts", "sandergeraedts@live.nl", "blub", "lorem ipsum dolor sit", "https://en.wikipedia.org/wiki/Blub_(water_park)");
        User user2 = new User("Bes Borbenk", "bosbisbas@live.nl", "blub", "lorem ipsum dolor sit", "https://en.wikipedia.org/wiki/Blub_(water_park)");
        User user3 = new User("Blub Blub", "Blub@blub.nl", "blub", "lorem ipsum dolor sit", "https://en.wikipedia.org/wiki/Blub_(water_park)");

        this.users = new ArrayList<User>();

        this.users.add(user1);
        this.users.add(user2);
        this.users.add(user3);
    }

    public void save(User user) {
        //Todo: Fix JPA
        this.users.add(user);
//        em.persist(user);
    }

    public List<User> findAll() {
        //Todo: Fix JPA
        return this.users;

//        return em.createNamedQuery("User.allUser").getResultList();
    }

    public User findById(Long id) {
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

}

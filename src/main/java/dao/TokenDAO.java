package dao;

import domain.Token;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TokenDAO {

    @PersistenceContext
    EntityManager em;

    public TokenDAO() {
    }

    public Token save(Token token) {
        em.persist(token);

        return token;
    }

    public void update(Token token) {
        em.persist(token);
    }

    public void remove(Token token) {
        em.remove(token);
    }

    public User findUserByKey(String key) {
        return (User) em.createNamedQuery("Token.getUser")
                .setParameter("key", key)
                .getResultList().get(0);
    }

    public Token findTokenByUserId(Long id) {
        return (Token) em.createNamedQuery("Token.getTokenByUserId")
                .setParameter("userId", id)
                .getResultList().get(0);
    }
}

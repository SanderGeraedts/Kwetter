package service;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.TokenDAO;
import dao.UserDAO;
import domain.Token;
import domain.User;

import java.security.Key;
import java.util.Date;

@Stateless
public class AuthService {
    @Inject
    TokenDAO tokenDAO;

    @Inject
    UserDAO userDAO;

    public Token addToken(String issuer, User user) {
        //Builds the JWT and serializes it to a compact, URL-safe string
        String _key = user.getName() + ":" + user.getPassword();

        return tokenDAO.save(new Token(_key, user));
    }

    public String getToken(String email, String password) {
        if (userDAO.findByCredentials(email, password) != null) {
            return email + ":" + password;
        } else {
            return "User not found";
        }

    }

    public User authenticate(String key) {
        String[] credentials = key.split(":");

        return userDAO.findByCredentials(credentials[0], credentials[1]);
    }
}

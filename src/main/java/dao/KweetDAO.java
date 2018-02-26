package dao;

import domain.Kweet;
import domain.User;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Todo: Fix JPA
@Singleton
@Startup
public class KweetDAO {

    private List<Kweet> kweets;

    public KweetDAO() {
        this.kweets = new ArrayList<Kweet>();

        User user = new User("Sander Geraedts", "sandergeraedts@live.nl", "blub", "lorem ipsum dolor sit", "https://en.wikipedia.org/wiki/Blub_(water_park)");

        Kweet kweet1 = new Kweet("Dit is een test 1", new Date(), user, 20);
        Kweet kweet2 = new Kweet("Dit is een test 2", new Date(), user, 20);
        Kweet kweet3 = new Kweet("Dit is een test 3", new Date(), user, 20);
        Kweet kweet4 = new Kweet("Dit is een test 4", new Date(), user, 20);
        Kweet kweet5 = new Kweet("Dit is een test 5", new Date(), user, 20);
        Kweet kweet6 = new Kweet("Dit is een test 6", new Date(), user, 20);
        Kweet kweet7 = new Kweet("Dit is een test 7", new Date(), user, 20);

        this.kweets.add(kweet1);
        this.kweets.add(kweet2);
        this.kweets.add(kweet3);
        this.kweets.add(kweet4);
        this.kweets.add(kweet5);
        this.kweets.add(kweet6);
        this.kweets.add(kweet7);
    }

    public List<Kweet> findAll() {
        return this.kweets;
    }

    public void save(Kweet kweet) {
        this.kweets.add(kweet);
    }
}

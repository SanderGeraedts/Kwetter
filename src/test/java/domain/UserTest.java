package domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user1;
    User user2;
    User user3;

    Kweet kweet1;
    Kweet kweet2;
    Kweet kweet3;

    @Before
    public void setUp() throws Exception {
        user1 = new User(
                "Sander Geraedts",
                "sandergeraedts@live.nl",
                "$2a$04$y2iE/aYWv4yKW.tp5FBAHu6uCmcDQATlp3aB3ig5d/7DOM5ekZKlO",
                "Dit is een test gebruiker",
                "http://via.placeholder.com/350x350"
        );
        user2 = new User(
                "Teun Willems",
                "tweun@live.nl",
                "$2a$04$y2iE/aYWv4yKW.tp5FBAHu6uCmcDQATlp3aB3ig5d/7DOM5ekZKlO",
                "Dit is een test gebruiker",
                "http://via.placeholder.com/350x350",
                UserRole.MODERATOR
        );
        user3 = new User(
                "Dennis Jenniskens",
                "ikben@thuiswantikben.ziek",
                "$2a$04$y2iE/aYWv4yKW.tp5FBAHu6uCmcDQATlp3aB3ig5d/7DOM5ekZKlO",
                "Dit is een test gebruiker",
                "http://via.placeholder.com/350x350"
        );

        kweet1 = new Kweet(
                "Dit is mijn eerste Kwekje",
                user1
        );

        kweet2 = new Kweet(
                "Dit is mijn tweede Kwekje",
                user1
        );

        kweet3 = new Kweet(
                "Dit is mijn derde Kwekje",
                user1
        );

        user1.addFollowing(user2);
        user1.addFollowing(user3);
        user2.addFollowing(user1);
        user2.addFollowing(user3);
        user3.addFollowing(user1);
        user3.addFollowing(user2);
    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("Sander Geraedts", user1.getName());
        Assert.assertEquals("Teun Willems", user2.getName());
        Assert.assertEquals("Dennis Jenniskens", user3.getName());

        Assert.assertNotEquals(" Sander Geraedts", user1.getName());
        Assert.assertNotEquals("Teun-Willems", user2.getName());
        Assert.assertNotEquals("Dennis_Jenniskens", user3.getName());
    }

    @Test
    public void setName() throws Exception {

        User _user1 = new User();
        User _user2 = new User();
        User _user3 = new User();

        _user1.setName("Sander Geraedts");
        _user2.setName("Teun Willems");
        _user3.setName("Dennis Jenniskens");

        Assert.assertEquals("Sander Geraedts", _user1.getName());
        Assert.assertEquals("Teun Willems", _user2.getName());
        Assert.assertEquals("Dennis Jenniskens", _user3.getName());

        Assert.assertNotEquals(" Sander Geraedts", _user1.getName());
        Assert.assertNotEquals("Teun-Willems", _user2.getName());
        Assert.assertNotEquals("Dennis_Jenniskens", _user3.getName());
    }

    @Test
    public void getEmail() throws Exception {
        Assert.assertEquals("sandergeraedts@live.nl", user1.getEmail());
        Assert.assertEquals("tweun@live.nl", user2.getEmail());
        Assert.assertEquals("ikben@thuiswantikben.ziek", user3.getEmail());

        Assert.assertNotEquals("Sander Geraedts", user1.getEmail());
        Assert.assertNotEquals(" tweun@live.nl", user2.getEmail());
        Assert.assertNotEquals("ikben@school", user3.getEmail());
    }

    @Test
    public void setEmail() throws Exception {
        User _user1 = new User();
        User _user2 = new User();
        User _user3 = new User();

        _user1.setEmail("sandergeraedts@live.nl");
        _user2.setEmail("tweun@live.nl");
        _user3.setEmail("ikben@thuiswantikben.ziek");

        Assert.assertEquals("sandergeraedts@live.nl", _user1.getEmail());
        Assert.assertEquals("tweun@live.nl", _user2.getEmail());
        Assert.assertEquals("ikben@thuiswantikben.ziek", _user3.getEmail());

        Assert.assertNotEquals("Sander Geraedts", _user1.getEmail());
        Assert.assertNotEquals(" tweun@live.nl", _user2.getEmail());
        Assert.assertNotEquals("ikben@school", _user3.getEmail());
    }

}
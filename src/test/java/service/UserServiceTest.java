package service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import dao.UserDAO;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        when(userDAO.findAll()).thenReturn(new ArrayList<>());

        assertThat(userService.findAll(), is(notNullValue()));
    }

    @Test
    public void create_happyFlow() {
        when(userDAO.save(any(User.class))).thenReturn(new User());

        User user1 = userService.create("name", "email", "passwordHash", "description", "avatar");
        User user2 = userService.create("name", "email", "passwordHash", "", "");

        assertThat(user1, is(User.class));
        assertThat(user1, is(notNullValue()));
        assertThat(user2, is(User.class));
        assertThat(user2, is(notNullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_unhappyFlow1() {
        when(userDAO.save(any(User.class))).thenReturn(new User());

        userService.create("blub", "", "", "", "");
    }
    @Test(expected = IllegalArgumentException.class)
    public void create_unhappyFlow2() {
        when(userDAO.save(any(User.class))).thenReturn(new User());

        userService.create("", "blub", "", "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_unhappyFlow3() {
        when(userDAO.save(any(User.class))).thenReturn(new User());

        userService.create("", "", "blub", "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_unhappyFlow4() {
        when(userDAO.save(any(User.class))).thenReturn(new User());

        userService.create("", "", "", "", "");
    }
}

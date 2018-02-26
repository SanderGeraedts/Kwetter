package boundary.rest;


import dao.UserDAO;
import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public List<User> findAll() {
        return userService.findAll();
    }

    @GET
    @Path("test")
    public String test() {
        return "Hello world";
    }

    @POST
    @Path("create")
    @Consumes("application/x-www-form-urlencoded")
    public User create(final MultivaluedMap<String, String> formParams) {

        User user = new User(
                formParams.getFirst("name"),
                formParams.getFirst("email"),
                formParams.getFirst("passwordHash"),
                formParams.getFirst("description"),
                formParams.getFirst("avatar"));

        userService.save(user);

        return user;
    }
}

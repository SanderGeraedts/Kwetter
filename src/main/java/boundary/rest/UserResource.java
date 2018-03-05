package boundary.rest;


import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
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

    @POST
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

    @GET
    @Path("{id : \\d+}")
    public User findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }

    @GET
    @Path("{id : \\d+}/follows")
    public List<User> userFollows(@PathParam("id") Long id) {
        return userService.findUserFollows(id);
    }

    @POST
    @Path("{id : \\d+}/follows")
    @Consumes("application/x-www-form-urlencoded")
    public User addUserFollows(@PathParam("id") Long id, final MultivaluedMap<String, String> formParams) {
        Long followerId = Long.parseLong(formParams.getFirst("followerId"));

        return userService.saveUserFollows(id, followerId);
    }

    @GET
    @Path("test")
    public String test() {
        return "Hello world";
    }
}

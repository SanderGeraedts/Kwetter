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

    @DELETE
    @Path("{id : \\d+}")
    public void removeUser(@PathParam("id") Long id) {
        userService.remove(id);
    }

    @PUT
    @Path("{id : \\d+}")
    @Consumes("application/x-www-form-urlencoded")
    public User update(@PathParam("id") Long id, final MultivaluedMap<String, String> formParams) {

        User user = userService.findById(id);

        String name = formParams.getFirst("name");
        String email = formParams.getFirst("email");
        String description = formParams.getFirst("description");
        String avatar = formParams.getFirst("name");

        if(name != null) {
            user.setName(name);
        }

        if(email != null) {
            user.setEmail(email);
        }

        if(description != null) {
            user.setDescription(description);
        }

        if(avatar != null) {
            user.setAvatar(avatar);
        }

        userService.update(user);

        return user;
    }

    @GET
    @Path("{id : \\d+}/follows")
    public List<User> userFollows(@PathParam("id") Long id) {
        return userService.findUserFollows(id);
    }

    @GET
    @Path("{id : \\d+}/followers")
    public List<User> userFollowers(@PathParam("id") Long id) {
        return userService.findUserFollowers(id);
    }

    @POST
    @Path("{id : \\d+}/follows")
    @Consumes("application/x-www-form-urlencoded")
    public List<User> addUserFollows(@PathParam("id") Long id, final MultivaluedMap<String, String> formParams) {
        Long followerId = Long.parseLong(formParams.getFirst("followerId"));

        return userService.saveUserFollows(id, followerId);
    }
}

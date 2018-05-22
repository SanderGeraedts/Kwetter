package boundary.rest;


import domain.Token;
import domain.User;
import service.AuthService;
import service.UserService;
import util.ResourceUriBuilder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("users")
@Stateless
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    AuthService authService;

    @Inject
    ResourceUriBuilder resourceUriBuilder;

    @Context
    UriInfo uriInfo;


    @GET
    public JsonArray findAll() {
        JsonArrayBuilder list = Json.createArrayBuilder();
        List<User> users = userService.findAll();

        for (User user : users) {
            list.add(user.toJson(
                    resourceUriBuilder.createResourceUri(
                            UserResource.class,
                            "findById",
                            user.getId(),
                            uriInfo)
            ));
        }

        return list.build();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public User create(final MultivaluedMap<String, String> formParams) {
        User user = userService.create(
                formParams.getFirst("name"),
                formParams.getFirst("email"),
                formParams.getFirst("password"),
                formParams.getFirst("description"),
                formParams.getFirst("avatar")
        );

        return user;
    }

    @GET
    @Path("{id : \\d+}")
    public JsonObject findById(@PathParam("id") Long id) {
        User user = userService.findById(id);

        final URI self = resourceUriBuilder.createResourceUri(
                UserResource.class,
                "findById",
                user.getId(),
                uriInfo
        );

        return user.toJson(self);
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

        if (name != null) {
            user.setName(name);
        }

        if (email != null) {
            user.setEmail(email);
        }

        if (description != null) {
            user.setDescription(description);
        }

        if (avatar != null) {
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

    @POST
    @Path("authenticate")
    @Consumes("application/x-www-form-urlencoded")
    public String authenticateUser(final MultivaluedMap<String, String> formParams) {
        return authService.getToken(
                formParams.getFirst("email"),
                formParams.getFirst("password")
        );
    }
}

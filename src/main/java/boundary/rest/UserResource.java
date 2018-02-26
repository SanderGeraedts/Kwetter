package boundary.rest;


import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("users")
@Stateless
public class UserResource {

    @GET
    @Path("test")
    public String test() {
        return "Hello world";
    }
}

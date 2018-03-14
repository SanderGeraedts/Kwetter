package boundary.rest;

import domain.Kweet;
import service.KweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("kweets")
@Stateless
public class KweetResource {

    @Inject
    KweetService kweetService;

    @GET
    public List<Kweet> findAll() {
        return kweetService.findAll();
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Kweet create(final MultivaluedMap<String, String> formParams) {
        return kweetService.create(
                formParams.getFirst("message"),
                Long.parseLong(formParams.getFirst("userId"))
        );
    }

    @GET
    @Path("{id : \\d+}")
    public Kweet find(@PathParam("id") Long id) {
        return kweetService.find(id);
    }

    @DELETE
    @Path("{id : \\d+}")
    public void remove(@PathParam("id") Long id) {
        kweetService.remove(id);
    }

    @PUT
    @Path("{id : \\d+}")
    public Kweet update(@PathParam("id") Long id,
                        final MultivaluedMap<String, String> formParams) {
        return kweetService.update(id, formParams.getFirst("message"));
    }

    @GET
    @Path("followedby/{id : \\d+}")
    public List<Kweet> findByUserId(@PathParam("id") Long id) {
        return kweetService.findAllByUserId(id);
    }

    @POST
    @Path("{id : \\d+}/heart")
    public Integer heart(@PathParam("id") Long id,
                         final MultivaluedMap<String, String> formParams) {
        Kweet kweet = kweetService.find(id);

        return kweetService.heartKweet(kweet,
                Long.parseLong(formParams.getFirst("userId")));
    }

    @POST
    @Path("{id : \\d+}/unheart")
    public Integer unheart(@PathParam("id") Long id,
                         final MultivaluedMap<String, String> formParams) {
        Kweet kweet = kweetService.find(id);

        return kweetService.unheartKweet(kweet,
                Long.parseLong(formParams.getFirst("userId")));
    }

}

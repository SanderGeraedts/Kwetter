package boundary.rest;

import domain.Kweet;
import service.KweetService;
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

@Path("kweets")
@Stateless
public class KweetResource {

    @Inject
    KweetService kweetService;

    @Inject
    ResourceUriBuilder resourceUriBuilder;

    @Context
    UriInfo uriInfo;

    @GET
    public JsonArray findAll() {

        JsonArrayBuilder list = Json.createArrayBuilder();
        List<Kweet> kweets = kweetService.findAll();

        for (Kweet kweet : kweets) {
            list.add(kweet.toJson(
                    resourceUriBuilder.createResourceUri(
                            KweetResource.class,
                            "find",
                            kweet.getId(),
                            uriInfo),
                    uriInfo.getBaseUri().toString()
            ));
        }

        return list.build();
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
    public JsonObject find(@PathParam("id") Long id) {
        Kweet kweet = kweetService.find(id);

        final URI self = resourceUriBuilder.createResourceUri(
                KweetResource.class,
                "find",
                kweet.getId(),
                uriInfo
        );
        return kweet.toJson(self, uriInfo.getBaseUri().toString());
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

package boundary.rest;


import domain.Notification;
import service.NotificationService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("notifications")
@Stateless
public class NotificationResource {

    @Inject
    NotificationService notificationService;

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Notification create(final MultivaluedMap<String, String> formParams) {
        return notificationService.create(
                formParams.getFirst("message"),
                Long.parseLong(formParams.getFirst("userId"))
        );
    }

    @GET
    @Path("{id : \\d+}")
    public List<Notification> findAllByUserId(@PathParam("id") Long id) {
        return notificationService.findAllByUserId(id);
    }

    @GET
    @Path("{id : \\d+}/unread")
    public List<Notification> findAllUnreadByUserId(@PathParam("id") Long id) {
        return notificationService.findAllUnreadByUserId(id);
    }

    @POST
    @Path("{id : \\d+}/read")
    @Consumes("application/x-www-form-urlencoded")
    public void read(Long id) {
        notificationService.read(id);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void remove(Long id) {
        notificationService.remove(id);
    }
}

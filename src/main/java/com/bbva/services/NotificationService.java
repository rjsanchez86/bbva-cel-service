package com.bbva.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("services")
public class NotificationService {

    // Soporte para peticiones POST (Seguimiento de especificación de código Java)
    @POST
    @Path("notification")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public NotificationDTO notificationProcessPost(NotificationDTO notificationDTO) {
        return process(notificationDTO);
    }

    // Soporte para peticiones PUT (Seguimiento de anexo formal BBVA)
    @PUT
    @Path("notification")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public NotificationDTO notificationProcessPut(NotificationDTO notificationDTO) {
        return process(notificationDTO);
    }

    // Lógica compartida
    private NotificationDTO process(NotificationDTO notificationDTO) {
        if (notificationDTO.getCodresp() == null || notificationDTO.getCodresp().isEmpty()) {
            notificationDTO.setCodresp("00");
        }
        return notificationDTO;
    }
}
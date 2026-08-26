package com.bbva.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("services")
public class NotificationService {

    @POST
    @Path("notification")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public NotificationDTO notificationProcess(NotificationDTO notificationDTO) {
        // Respuesta de prueba aprobando la operación
        if (notificationDTO.getCodresp() == null || notificationDTO.getCodresp().isEmpty()) {
            notificationDTO.setCodresp("00");
        }
        return notificationDTO;
    }
}
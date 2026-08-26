package com.bbva.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("services")
public class NotificationService {

    @POST
    @Path("notification")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public NotificationDTO notificationProcessPost(NotificationDTO dto) {
        return process(dto);
    }

    @PUT
    @Path("notification")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public NotificationDTO notificationProcessPut(NotificationDTO dto) {
        return process(dto);
    }

    private NotificationDTO process(NotificationDTO dto) {
        // Asignar código de respuesta exitoso si no viene indicado
        if (dto.getCodresp() == null || dto.getCodresp().isEmpty()) {
            dto.setCodresp("00");
        }

        // Si es una Consulta (CON) y se aprueba, asignamos vigencia y montos permitidos
        if ("CON".equals(dto.getTipoper())) {
            if (dto.getFhvigem() == null || dto.getFhvigem().isEmpty()) {
                dto.setFhvigem("2026-12-31");
            }
            if (dto.getImminem() == null || dto.getImminem() == 0) {
                dto.setImminem(207.05);
            }
            if (dto.getImmaxem() == null || dto.getImmaxem() == 0) {
                dto.setImmaxem(1000.00);
            }
            if (dto.getImptota() == null || dto.getImptota() == 0) {
                dto.setImptota(650.91);
            }
        }

        // Si es Pago o Cobro, asegurar hora de pago
        if (("PAG".equals(dto.getTipoper()) || "COB".equals(dto.getTipoper())) && (dto.getHorpago() == null || dto.getHorpago().isEmpty())) {
            dto.setHorpago("163632");
        }

        // Inicializar cadenas vacías en atributos nulos para coincidir con la plantilla BBVA
        if (dto.getAutoriz() == null) dto.setAutoriz("");
        if (dto.getCertiem() == null) dto.setCertiem("");
        if (dto.getClcurem() == null) dto.setClcurem("");
        if (dto.getCodfijo() == null) dto.setCodfijo("");
        if (dto.getFeccap() == null) dto.setFeccap("");
        if (dto.getFecpago() == null) dto.setFecpago("");
        if (dto.getFilesp1() == null) dto.setFilesp1("");
        if (dto.getFilesp2() == null) dto.setFilesp2("");
        if (dto.getFillmas() == null) dto.setFillmas("");
        if (dto.getFillnum() == null) dto.setFillnum("");
        if (dto.getFlpagem() == null) dto.setFlpagem("");
        if (dto.getTppagem() == null) dto.setTppagem("");
        if (dto.getImmaxem() == null) dto.setImmaxem(0.0);
        if (dto.getImminem() == null) dto.setImminem(0.0);
        if (dto.getImptota() == null) dto.setImptota(0.0);
        if (dto.getIdmsjpa() == null) dto.setIdmsjpa(0L);
        if (dto.getNummens() == null) dto.setNummens(0L);

        return dto;
    }
}
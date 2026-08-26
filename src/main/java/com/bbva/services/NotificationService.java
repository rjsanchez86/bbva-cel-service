package com.bbva.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

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
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public NotificationDTO notificationProcessPost(NotificationDTO dto) {
        return process(dto);
    }

    @PUT
    @Path("notification")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
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
            // 1. Fecha de vigencia dinámica: Fecha actual + 1 año (formato YYYY-MM-DD)
            if (dto.getFhvigem() == null || dto.getFhvigem().isEmpty()) {
                LocalDate fechaVigencia = LocalDate.now().plusYears(1);
                dto.setFhvigem(fechaVigencia.format(DateTimeFormatter.ISO_LOCAL_DATE));
            }

            // 2. Importe total dinámico: Monto aleatorio entre $100.00 y $5,000.00
            if (dto.getImptota() == null || dto.getImptota() == 0) {
                double imptota = ThreadLocalRandom.current().nextDouble(100.00, 5000.00);
                // Redondeo a 2 decimales
                dto.setImptota(Math.round(imptota * 100.0) / 100.0);
            }

            // 3. Mínimo dinámico: 10% del importe total
            if (dto.getImminem() == null || dto.getImminem() == 0) {
                double imminem = dto.getImptota() * 0.10;
                dto.setImminem(Math.round(imminem * 100.0) / 100.0);
            }

            // 4. Máximo dinámico: Igual al importe total o 150% del total
            if (dto.getImmaxem() == null || dto.getImmaxem() == 0) {
                dto.setImmaxem(dto.getImptota());
            }
        }

        // Si es Pago o Cobro, asegurar hora de pago

        if (("PAG".equals(dto.getTipoper()) || "COB".equals(dto.getTipoper()))
                && (dto.getHorpago() == null || dto.getHorpago().isEmpty())) {

            // Obtiene la hora actual del servidor en formato HHmmss (6 dígitos)
            String horaActual = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
            dto.setHorpago(horaActual);
        }

        // Inicializar cadenas vacías en atributos nulos para coincidir con la plantilla
        // BBVA
        if (dto.getAutoriz() == null)
            dto.setAutoriz("");
        if (dto.getCertiem() == null)
            dto.setCertiem("");
        if (dto.getClcurem() == null)
            dto.setClcurem("");
        if (dto.getCodfijo() == null)
            dto.setCodfijo("");
        if (dto.getFeccap() == null)
            dto.setFeccap("");
        if (dto.getFecpago() == null)
            dto.setFecpago("");
        if (dto.getFilesp1() == null)
            dto.setFilesp1("");
        if (dto.getFilesp2() == null)
            dto.setFilesp2("");
        if (dto.getFillmas() == null)
            dto.setFillmas("");
        if (dto.getFillnum() == null)
            dto.setFillnum("");
        if (dto.getFlpagem() == null)
            dto.setFlpagem("");
        if (dto.getTppagem() == null)
            dto.setTppagem("");
        if (dto.getImmaxem() == null)
            dto.setImmaxem(0.0);
        if (dto.getImminem() == null)
            dto.setImminem(0.0);
        if (dto.getImptota() == null)
            dto.setImptota(0.0);
        if (dto.getIdmsjpa() == null)
            dto.setIdmsjpa(0L);
        if (dto.getNummens() == null)
            dto.setNummens(0L);

        return dto;
    }
}
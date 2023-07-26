package com.example.DoubleV.service;

import com.example.DoubleV.dto.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TicketHelper {
    // Método para validar una solicitud de Ticket (TicketRequest).
    public void validate(TicketRequest request){
        // Verificar si el estado del ticket es diferente de nulo.
        if(request.getStatus() != null){
            // Si el estado no es "Abierto" ni "Cerrado", lanzar una excepción.
            if(!request.getStatus().equals("Abierto") && !request.getStatus().equals("Cerrado")){
                throw new IllegalStateException("El estado del ticket no es válido. Se esperaba 'Abierto ó Cerrado'.");
            }
        }
    }

    // Método estático para establecer la hora en una fecha dada (Date).
    public static Date setTimeToDate(Date date, short hours, short minutes, short seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);

        return calendar.getTime();
    }
}

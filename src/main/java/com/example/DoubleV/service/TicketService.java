package com.example.DoubleV.service;

import com.example.DoubleV.dto.PaginationResponse;
import com.example.DoubleV.dto.TicketFilterRequest;
import com.example.DoubleV.dto.TicketRequest;
import com.example.DoubleV.dto.TicketResponse;
import com.example.DoubleV.model.TicketEntity;

import java.util.List;

public interface TicketService {
    // Crea un nuevo ticket.
    TicketResponse create(TicketRequest request);

    //Obtiene todos los tickets con filtros opcionales.
    PaginationResponse<TicketResponse> getAllWithFilter(TicketFilterRequest request);

    // Obtiene un ticket por su ID.
    TicketResponse getById(Long id);

    //Actualiza los datos de un ticket existente.
    TicketResponse update(Long id, TicketRequest request);

    //Elimina un ticket por su ID.
    void delete(Long id);
}




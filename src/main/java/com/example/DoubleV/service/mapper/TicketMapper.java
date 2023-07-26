package com.example.DoubleV.service.mapper;

import com.example.DoubleV.dto.PaginationResponse;
import com.example.DoubleV.dto.TicketFilterRequest;
import com.example.DoubleV.dto.TicketRequest;
import com.example.DoubleV.dto.TicketResponse;
import com.example.DoubleV.model.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TicketMapper {

    // Método para convertir una solicitud (TicketRequest) en una entidad TicketEntity.
    public TicketEntity convert(TicketRequest request){
        TicketEntity entity = TicketEntity.builder()
                .updateDate(request.getUpdateDate())
                .creationDate(request.getCreationDate())
                .user(request.getUser())
                .status(request.getStatus())
                .build();

        return entity;
    }
    // Método para convertir una entidad TicketEntity en una respuesta (TicketResponse).
    public TicketResponse convert(TicketEntity entity){
        TicketResponse response = TicketResponse.builder()
                .id(entity.getId())
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .user(entity.getUser())
                .status(entity.getStatus())
                .build();

        return response;
    }
    // Método para convertir una lista de entidades TicketEntity en una lista de respuestas (TicketResponse).
    public List<TicketResponse> convert(List<TicketEntity> entities){
        return entities.stream().map(x -> convert(x)).collect(Collectors.toList());
    }

    // Método para convertir la paginación de entidades TicketEntity y una solicitud (TicketFilterRequest) en una respuesta paginada (PaginationResponse<TicketResponse>).
    public PaginationResponse<TicketResponse> convert(TicketFilterRequest request, Page<TicketEntity> pagination) {
        PaginationResponse<TicketResponse> paginationResponse = new PaginationResponse<>();
        paginationResponse.setItems(convert(pagination.getContent()));
        paginationResponse.setPage(request.getPageable().getPageNumber());
        paginationResponse.setTotalPages(pagination.getTotalPages());
        paginationResponse.setSizeItems(pagination.getTotalElements());
        paginationResponse.setNextPage(pagination.nextPageable());
        paginationResponse.setPreviousPage(pagination.previousPageable());
        paginationResponse.setSizePage(pagination.getNumberOfElements());

        return paginationResponse;
    }

    // Método para actualizar una entidad TicketEntity con los valores de una solicitud (TicketRequest).
    public TicketEntity convert(TicketEntity entity, TicketRequest request){
        entity.setUser(request.getUser() != null ? request.getUser() : entity.getUser());
        entity.setCreationDate(request.getCreationDate() != null ? request.getCreationDate() : entity.getCreationDate());
        entity.setUpdateDate(request.getUpdateDate() != null ? request.getUpdateDate() : entity.getUpdateDate());
        entity.setStatus(request.getStatus() != null ? request.getStatus() : entity.getStatus());

        return entity;
    }
}

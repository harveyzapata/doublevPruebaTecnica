package com.example.DoubleV.service;
import com.example.DoubleV.Error.NotFoundElementException;
import com.example.DoubleV.dto.PaginationResponse;
import com.example.DoubleV.dto.TicketFilterRequest;
import com.example.DoubleV.dto.TicketRequest;
import com.example.DoubleV.dto.TicketResponse;
import com.example.DoubleV.model.TicketEntity;
import com.example.DoubleV.repository.TicketRepository;
import com.example.DoubleV.service.mapper.TicketMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import static com.example.DoubleV.Error.ErrorMessage.TICKET_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final TicketHelper ticketHelper;

    @Override
    public TicketResponse create(TicketRequest request) {
        // Valida la solicitud del ticket antes de proceder.
        ticketHelper.validate(request);
        // Convierte la solicitud del ticket (TicketRequest) a una entidad TicketEntity.
        TicketEntity entity = ticketMapper.convert(request);
        // Guarda la entidad TicketEntity en la base de datos.
        var saved = ticketRepository.save(entity);
        // Convierte la entidad guardada a una respuesta del ticket (TicketResponse) y la devuelve.
        return ticketMapper.convert(saved);
    }

    @Override
    public PaginationResponse<TicketResponse> getAllWithFilter(TicketFilterRequest request){
        // Realizar una consulta a la base de datos utilizando especificaciones (Specifications) para filtrar los tickets.
        var tickets = ticketRepository.findAll((Specification<TicketEntity>) (root, cq, cb) -> {
            // Construir lista de predicados para aplicar los filtros.
            List<Predicate> predicates = new ArrayList<>();
            // Agregar predicados para los filtros según los valores proporcionados en la solicitud.
            if(Objects.nonNull(request.getStatus())){
                predicates.add(cb.equal(root.get("status"), request.getStatus()));
            }

            if (Objects.nonNull(request.getId())){
                predicates.add(cb.equal(root.get("id"), request.getId()));
            }

            if (Objects.nonNull(request.getUser())){
                predicates.add(cb.like(root.get("user"), "%" + request.getUser() + "%"));
            }

            if(Objects.nonNull(request.getUpdateDate())){
                Date startDate = TicketHelper.setTimeToDate(request.getUpdateDate(), (short) 0, (short) 0, (short) 0);
                Date endDate = TicketHelper.setTimeToDate(request.getUpdateDate(), (short) 23, (short) 59, (short) 59);

                predicates.add(cb.between(root.get("updateDate"), startDate, endDate));
            }

            if(Objects.nonNull(request.getCreationDate())){
                Date startDate = TicketHelper.setTimeToDate(request.getCreationDate(), (short) 0, (short) 0, (short) 0);
                Date endDate = TicketHelper.setTimeToDate(request.getCreationDate(), (short) 23, (short) 59, (short) 59);

                predicates.add(cb.between(root.get("creationDate"), startDate, endDate));
            }
            // Construir la consulta con los predicados y configuraciones adicionales.
            cq.where(predicates.toArray(new Predicate[]{}));
            cq.distinct(true);
            cq.orderBy(cb.desc(root.get("id")));
            // Devolver el grupo de restricción de la consulta.
            return cq.getGroupRestriction();
        }, request.getPageable());

        return ticketMapper.convert(request, tickets);
    }

    @Override
    public TicketResponse getById(Long id) {
        // Buscar el ticket por su ID en la base de datos.
        var optionalTicket = ticketRepository.findById(id).orElseThrow(() -> new NotFoundElementException(TICKET_NOT_FOUND));;
        // Convertir la entidad TicketEntity a una respuesta del ticket (TicketResponse) y devolverla.
        return ticketMapper.convert(optionalTicket);

    }

    @Override
    public TicketResponse update(Long id, TicketRequest request) {
        // Validar la solicitud del ticket antes de proceder.
        ticketHelper.validate(request);
        // Buscar el ticket por su ID en la base de datos.
        var optionalTicket = ticketRepository
                .findById(id).orElseThrow(() -> new NotFoundElementException(TICKET_NOT_FOUND));
        // Convertir la entidad TicketEntity existente con los valores de la solicitud (TicketRequest).
        TicketEntity entity = ticketMapper.convert(optionalTicket, request);
        // Guardar la entidad actualizada en la base de datos.
        var saved = ticketRepository.save(entity);
        // Convertir la entidad guardada a una respuesta del ticket (TicketResponse) y devolverla.
        return ticketMapper.convert(saved);
    }

    @Override
    public void delete(Long id) {
        // Buscar el ticket por su ID en la base de datos.
        var ticket = ticketRepository
                .findById(id).orElseThrow(() -> new NotFoundElementException(TICKET_NOT_FOUND));
        // Eliminar el ticket de la base de datos.
        ticketRepository.delete(ticket);
    }
}

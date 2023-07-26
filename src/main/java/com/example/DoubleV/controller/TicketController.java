package com.example.DoubleV.controller;

import com.example.DoubleV.dto.PaginationResponse;
import com.example.DoubleV.dto.TicketFilterRequest;
import com.example.DoubleV.dto.TicketRequest;
import com.example.DoubleV.dto.TicketResponse;
import com.example.DoubleV.model.TicketEntity;
import com.example.DoubleV.service.TicketServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/tickets")
@SecurityRequirement(name = "api")
@RequiredArgsConstructor
@CrossOrigin(value = "*", maxAge = 3600)

public class TicketController {

    private final TicketServiceImpl ticketService;

    // Método para crear un nuevo Ticket.
    @PostMapping
    @Operation(summary = "Create a new Ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Body is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error in call some provider", content = @Content)})
    public ResponseEntity<TicketResponse> create(@Valid @RequestBody TicketRequest request) {
        return new ResponseEntity<>(ticketService.create(request), HttpStatus.CREATED);
    }

    // Método para obtener un Ticket por su ID.
    @GetMapping("/{id}")
    @Operation(summary = "Get Ticket")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Get Ticket",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketEntity.class)) }),
            @ApiResponse(responseCode = "400", description = "Body is incorrect",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error in call some provider",
                    content = @Content)})
    public ResponseEntity<TicketResponse> getById(@PathVariable Long id){
        System.out.println(ticketService.getById(id));
        if(ticketService.getById(id) != null){
            return ResponseEntity.ok(ticketService.getById(id));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Método para obtener todos los Tickets con filtros opcionales de búsqueda y paginación.
    @GetMapping
    @Operation(summary = "Get all Ticket with filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all Ticket with filters",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error in call some provider", content = @Content)})
    public ResponseEntity<PaginationResponse<TicketResponse>> findAll(@RequestParam(required = false) Long id,
                                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date updateDate,
                                                                      @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date creationDate,
                                                                      @RequestParam(required = false) String status,
                                                                      @RequestParam(required = false) String user,
                                                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                                                      @RequestParam(required = false, defaultValue = "10") Integer size) {
        // Construir el objeto TicketFilterRequest con los filtros proporcionados
        var request = TicketFilterRequest.builder()
                .id(id)
                .updateDate(updateDate)
                .creationDate(creationDate)
                .status(status)
                .user(user)
                .build();
        // Configurar paginación en el objeto request
        request.setPageable(PageRequest.of(page, size));

        return new ResponseEntity<>(ticketService.getAllWithFilter(request), HttpStatus.OK);
    }

    // Método para actualizar los datos de un Ticket existente.
    @PutMapping("/{id}")
    @Operation(summary = "Update application rud")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update application rud",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Body is incorrect",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error in call some provider",
                    content = @Content)})
    public ResponseEntity<TicketResponse> updateApplicationRUD(@PathVariable Long id, @RequestBody TicketRequest ticket){
        return ResponseEntity.ok(ticketService.update(id, ticket));
    }

    // Método para eliminar un Ticket por su ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Void.class)) }),
            @ApiResponse(responseCode = "404", description = "Ticket not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error in call some provider", content = @Content)})
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

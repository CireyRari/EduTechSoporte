package com.Ticket.TSoporte.controller;

import com.Ticket.TSoporte.model.Ticket;
import com.Ticket.TSoporte.service.TicketService;
import com.Ticket.TSoporte.util.TicketModelAssembler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    //prueba
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // Crear un ticket
    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid@RequestBody Ticket ticket) {
        Ticket nuevoTicket = ticketService.crearTicket(ticket);
        return ResponseEntity.ok(nuevoTicket);
    }

    // Cambiar estado de un ticket por id
    @PutMapping("/{id}/estado")
    public ResponseEntity<Ticket> cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        Ticket ticketActualizado = ticketService.cambiarEstado(id, nuevoEstado);
        return ResponseEntity.ok(ticketActualizado);
    }

    // Borrar un ticket por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTicket(@PathVariable Long id) {
        ticketService.borrarTicket(id);
        return ResponseEntity.noContent().build();
    }

    // Listar tickets por tipoProblema (ejemplo: /api/tickets/tipo?tipo=Simple)
    @GetMapping("/tipo")
    public ResponseEntity<List<Ticket>> listarPorTipo(@RequestParam String tipo) {
        List<Ticket> tickets = ticketService.listarPorTipo(tipo);
        return ResponseEntity.ok(tickets);
    }

    // Asignar técnico a un ticket (PUT con id ticket e id tecnico)
    @PutMapping("/{idTicket}/asignar-tecnico/{idTecnico}")
    public ResponseEntity<Ticket> asignarTecnico(@PathVariable Long idTicket, @PathVariable Long idTecnico) {
        Ticket ticketAsignado = ticketService.asignarTecnico(idTicket, idTecnico);
        return ResponseEntity.ok(ticketAsignado);
    }

        @Autowired
    private TicketModelAssembler assembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Ticket>> obtenerPorId(@PathVariable Long id) {
        Ticket ticket = ticketService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(ticket));
    }


    @GetMapping
public ResponseEntity<CollectionModel<EntityModel<Ticket>>> listarTodos() {
    List<Ticket> tickets = ticketService.listarTodos();

    List<EntityModel<Ticket>> ticketModels = tickets.stream()
            .map(assembler::toModel)
            .toList();
    CollectionModel<EntityModel<Ticket>> collection = CollectionModel.of(
            ticketModels,
            linkTo(methodOn(TicketController.class).listarTodos()).withSelfRel()
    );

    return ResponseEntity.ok(collection);
}

}
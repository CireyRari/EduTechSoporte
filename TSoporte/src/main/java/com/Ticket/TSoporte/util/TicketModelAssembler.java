package com.Ticket.TSoporte.util;


import com.Ticket.TSoporte.controller.TicketController;
import com.Ticket.TSoporte.model.Ticket;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@Component
public class TicketModelAssembler implements RepresentationModelAssembler<Ticket, EntityModel<Ticket>> {

    @Override
    public EntityModel<Ticket> toModel(Ticket ticket) {
        return EntityModel.of(ticket,
                linkTo(methodOn(TicketController.class).obtenerPorId(ticket.getId())).withSelfRel(),
                linkTo(methodOn(TicketController.class).listarTodos()).withRel("todos-los-tickets"),
                linkTo(methodOn(TicketController.class).cambiarEstado(ticket.getId(), "Resuelto")).withRel("cambiar-estado"),
                linkTo(methodOn(TicketController.class).asignarTecnico(ticket.getId(), 1L)).withRel("asignar-tecnico")
        );
    }
}
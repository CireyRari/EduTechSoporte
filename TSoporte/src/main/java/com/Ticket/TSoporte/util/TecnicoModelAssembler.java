package com.Ticket.TSoporte.util;

import com.Ticket.TSoporte.controller.TecnicoController;
import com.Ticket.TSoporte.model.Tecnico;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TecnicoModelAssembler implements RepresentationModelAssembler<Tecnico, EntityModel<Tecnico>> {

    @Override
    public EntityModel<Tecnico> toModel(Tecnico tecnico) {
        return EntityModel.of(tecnico,
                linkTo(methodOn(TecnicoController.class).buscarPorId(tecnico.getId())).withSelfRel(),
                linkTo(methodOn(TecnicoController.class).listarTodos()).withRel("todos-los-tecnicos"),
                linkTo(methodOn(TecnicoController.class).borrarTecnico(tecnico.getId())).withRel("borrar")
        );
    }
}

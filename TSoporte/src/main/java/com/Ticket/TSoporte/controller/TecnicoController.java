package com.Ticket.TSoporte.controller;

import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.service.TecnicoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Ticket.TSoporte.util.TecnicoModelAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@RequiredArgsConstructor
public class TecnicoController {

    
    private final TecnicoModelAssembler assembler;
    private final TecnicoService tecnicoService;

    //prueba
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // Crear técnico
    @PostMapping
    public ResponseEntity<Tecnico> crearTecnico(@Valid@RequestBody Tecnico tecnico) {
        Tecnico nuevoTecnico = tecnicoService.crearTecnico(tecnico);
        return ResponseEntity.ok(nuevoTecnico);
    }

    // Listar todos los técnicos
    @GetMapping
public ResponseEntity<CollectionModel<EntityModel<Tecnico>>> listarTodos() {
    List<Tecnico> tecnicos = tecnicoService.listarTodos();
    List<EntityModel<Tecnico>> tecnicoModels = tecnicos.stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

    CollectionModel<EntityModel<Tecnico>> collection = CollectionModel.of(
            tecnicoModels,
            linkTo(methodOn(TecnicoController.class).listarTodos()).withSelfRel()
    );

    return ResponseEntity.ok(collection);
}
    // Buscar técnico por id
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Tecnico>> buscarPorId(@PathVariable Long id) {
        Tecnico tecnico = tecnicoService.buscarPorId(id);
        return ResponseEntity.ok(assembler.toModel(tecnico));
}

    // Borrar técnico por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarTecnico(@PathVariable Long id) {
        tecnicoService.borrarTecnico(id);
        return ResponseEntity.noContent().build();
    }
}
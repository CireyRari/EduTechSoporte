package com.Ticket.TSoporte.service;

import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    // Crear técnico
    public Tecnico crearTecnico(Tecnico tecnico) {
        return tecnicoRepository.save(tecnico);
    }

    // Listar todos los técnicos
    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    // Buscar técnico por id
    public Tecnico buscarPorId(Long id) {
        return tecnicoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado con id: " + id));
    }

    // Eliminar técnico por id
    public void borrarTecnico(Long id) {
        if (!tecnicoRepository.existsById(id)) {
            throw new RuntimeException("Técnico no encontrado con id: " + id);
        }
        tecnicoRepository.deleteById(id);
    }
}

package com.Ticket.TSoporte.service;


import com.Ticket.TSoporte.model.Ticket;
import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.repository.TicketRepository;
import com.Ticket.TSoporte.repository.TecnicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TecnicoRepository tecnicoRepository;

    // Crear un nuevo ticket
    public Ticket crearTicket(Ticket ticket) {
        ticket.setEstadoTicket("ABIERTO"); // Estado por defecto al crear
        return ticketRepository.save(ticket);
    }

    // Cambiar estado de un ticket por id
    public Ticket cambiarEstado(Long idTicket, String nuevoEstado) {
        Ticket ticket = ticketRepository.findById(idTicket)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + idTicket));
        ticket.setEstadoTicket(nuevoEstado.toUpperCase());
        return ticketRepository.save(ticket);
    }

    // Borrar un ticket por id
    public void borrarTicket(Long idTicket) {
        if (!ticketRepository.existsById(idTicket)) {
            throw new RuntimeException("Ticket no encontrado con id: " + idTicket);
        }
        ticketRepository.deleteById(idTicket);
    }

    // Listar todos los tickets
    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    // Listar tickets por tipoProblema (simple)
    public List<Ticket> listarPorTipo(String tipoProblema) {
        return ticketRepository.findByTipoProblema(tipoProblema.toUpperCase());
    }

    // Asignar técnico a un ticket por id
    public Ticket asignarTecnico(Long idTicket, Long idTecnico) {
        Ticket ticket = ticketRepository.findById(idTicket)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + idTicket));
        Tecnico tecnico = tecnicoRepository.findById(idTecnico)
            .orElseThrow(() -> new RuntimeException("Técnico no encontrado con id: " + idTecnico));
        ticket.setTecnico(tecnico);
        return ticketRepository.save(ticket);
    }
    
    public Ticket buscarPorId(Long id) {
    return ticketRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Ticket no encontrado con id: " + id));
}
   

}

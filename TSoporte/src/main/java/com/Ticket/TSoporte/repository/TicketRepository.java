package com.Ticket.TSoporte.repository;

import com.Ticket.TSoporte.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    List<Ticket> findByEstadoTicket(String estadoTicket);
    List<Ticket> findByTipoProblema(String tipoProblema);
    List<Ticket> findByTecnicoId(Long tecnicoId);
}

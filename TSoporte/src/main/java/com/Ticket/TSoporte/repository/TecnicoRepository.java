package com.Ticket.TSoporte.repository;


import com.Ticket.TSoporte.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    
}

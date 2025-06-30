package com.Ticket.TSoporte;

import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.model.Ticket;
import com.Ticket.TSoporte.repository.TecnicoRepository;
import com.Ticket.TSoporte.repository.TicketRepository;
import com.Ticket.TSoporte.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TecnicoRepository tecnicoRepository;

    @InjectMocks
    private TicketService ticketService;

    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticket = new Ticket();
        ticket.setId(1L);
        ticket.setTipoProblema("RED");
        ticket.setEstadoTicket("ABIERTO");
    }

    @Test
    void testCrearTicket() {
        when(ticketRepository.save(ticket)).thenReturn(ticket);
        Ticket result = ticketService.crearTicket(ticket);
        assertEquals("ABIERTO", result.getEstadoTicket());
    }

    @Test
    void testCambiarEstado_success() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any())).thenReturn(ticket);
        Ticket result = ticketService.cambiarEstado(1L, "cerrado");
        assertEquals("CERRADO", result.getEstadoTicket());
    }

    @Test
    void testCambiarEstado_ticketNoEncontrado() {
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ticketService.cambiarEstado(99L, "cerrado"));
    }

    @Test
    void testBorrarTicket_success() {
        when(ticketRepository.existsById(1L)).thenReturn(true);
        doNothing().when(ticketRepository).deleteById(1L);
        ticketService.borrarTicket(1L);
        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBorrarTicket_ticketNoExiste() {
        when(ticketRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> ticketService.borrarTicket(99L));
    }

    @Test
    void testListarTodos() {
        when(ticketRepository.findAll()).thenReturn(List.of(ticket));
        List<Ticket> result = ticketService.listarTodos();
        assertEquals(1, result.size());
    }

    @Test
    void testListarPorTipo() {
        when(ticketRepository.findByTipoProblema("RED")).thenReturn(List.of(ticket));
        List<Ticket> result = ticketService.listarPorTipo("red");
        assertEquals("RED", result.get(0).getTipoProblema());
    }

    @Test
    void testAsignarTecnico_success() {
        Tecnico tecnico = new Tecnico();
        tecnico.setId(10L);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(tecnicoRepository.findById(10L)).thenReturn(Optional.of(tecnico));
        when(ticketRepository.save(any())).thenReturn(ticket);

        Ticket result = ticketService.asignarTecnico(1L, 10L);
        assertNotNull(result);
    }

    @Test
    void testAsignarTecnico_ticketNoExiste() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ticketService.asignarTecnico(1L, 10L));
    }

    @Test
    void testAsignarTecnico_tecnicoNoExiste() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(tecnicoRepository.findById(10L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ticketService.asignarTecnico(1L, 10L));
    }

    @Test
    void testBuscarPorId_success() {
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        Ticket result = ticketService.buscarPorId(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void testBuscarPorId_ticketNoExiste() {
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> ticketService.buscarPorId(99L));
    }
}

package com.Ticket.TSoporte;

import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.repository.TecnicoRepository;
import com.Ticket.TSoporte.service.TecnicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TecnicoServiceTest {

    @Mock
    private TecnicoRepository tecnicoRepository;

    @InjectMocks
    private TecnicoService tecnicoService;

    private Tecnico tecnico;

    @BeforeEach
    void setUp() {
        tecnico = new Tecnico();
        tecnico.setId(1L);
        tecnico.setNombre("Juan");
    }

    @Test
    void testCrearTecnico() {
        when(tecnicoRepository.save(tecnico)).thenReturn(tecnico);
        Tecnico result = tecnicoService.crearTecnico(tecnico);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testListarTodos() {
        when(tecnicoRepository.findAll()).thenReturn(List.of(tecnico));
        List<Tecnico> result = tecnicoService.listarTodos();
        assertEquals(1, result.size());
    }

    @Test
    void testBuscarPorId_encontrado() {
        when(tecnicoRepository.findById(1L)).thenReturn(Optional.of(tecnico));
        Tecnico result = tecnicoService.buscarPorId(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void testBuscarPorId_noEncontrado() {
        when(tecnicoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> tecnicoService.buscarPorId(99L));
    }

    @Test
    void testBorrarTecnico_existente() {
        when(tecnicoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tecnicoRepository).deleteById(1L);
        tecnicoService.borrarTecnico(1L);
        verify(tecnicoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testBorrarTecnico_noExiste() {
        when(tecnicoRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> tecnicoService.borrarTecnico(99L));
    }
}

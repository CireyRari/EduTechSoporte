package com.Ticket.TSoporte.controller;

import com.Ticket.TSoporte.util.DataLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestDataController {

    private final DataLoader dataLoader;

    @PostMapping("/cargar")
    public ResponseEntity<String> cargarDatosDePrueba() {
        dataLoader.cargarDatosDePrueba();
        return ResponseEntity.ok("Datos de prueba generados correctamente");
    }
}

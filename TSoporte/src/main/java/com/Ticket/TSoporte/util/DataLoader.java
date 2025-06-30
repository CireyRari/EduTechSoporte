package com.Ticket.TSoporte.util;

import com.Ticket.TSoporte.model.Tecnico;
import com.Ticket.TSoporte.model.Ticket;
import com.Ticket.TSoporte.repository.TecnicoRepository;
import com.Ticket.TSoporte.repository.TicketRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final TecnicoRepository tecnicoRepository;
    private final TicketRepository ticketRepository;

    private final Faker faker = new Faker(new Locale("es"));
    private final Random random = new Random();

    private final String[] frasesSoporte = {
        "No puedo acceder al sistema.",
        "La aplicación se cierra inesperadamente.",
        "No se guardan los cambios al hacer clic en guardar.",
        "La pantalla se queda en blanco después de iniciar sesión.",
        "El sistema arroja un error 500 al cargar los cursos.",
        "No puedo subir archivos en la plataforma.",
        "El botón de enviar no responde.",
        "El curso no aparece en la lista asignada.",
        "No se puede cambiar la contraseña.",
        "La interfaz está desconfigurada en el celular.",
        "La conexión con la base de datos falla frecuentemente.",
        "Algunos módulos del curso cargan muy lento.",
        "Error al procesar el pago del curso.",
        "La sesión se cierra automáticamente.",
        "No llegan notificaciones por correo.",
        "El video del curso no se reproduce.",
        "La página se queda cargando infinitamente.",
        "Los datos del perfil no se actualizan.",
        "El sistema me expulsa al intentar entrar a clases.",
        "Las actividades no se marcan como completadas."
    };

    //@PostConstruct
    public void cargarDatosDePrueba() {
        // Crear 5 técnicos
        for (int i = 0; i < 5; i++) {
            Tecnico tecnico = new Tecnico();
            tecnico.setNombre(faker.name().fullName());
            tecnico.setCargo(faker.job().title());
            tecnicoRepository.save(tecnico);
        }

        // Crear 10 tickets con frases realistas en español
        for (int i = 0; i < 10; i++) {
            Ticket ticket = new Ticket();

            ticket.setNombre("Problema con " + faker.app().name());

            ticket.setDescripcion("El usuario reporta: " + frasesSoporte[random.nextInt(frasesSoporte.length)]);

            ticket.setTipoProblema(random.nextBoolean() ? "Simple" : "Complejo");
            ticket.setEstadoTicket("Abierto");
            ticket.setCurso_adjunto(faker.number().numberBetween(100, 200));

            // No se asigna técnico
            ticketRepository.save(ticket);
        }

        System.out.println("✅ Datos de prueba generados con frases realistas en español.");
    }
}
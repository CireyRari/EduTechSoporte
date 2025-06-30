package com.Ticket.TSoporte.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

     //Asigna id como pk y le asigna un valor autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del ticket", example = "1")
    private Long id;
    

    @Schema(description = "Nombre del ticket", example = "Error al iniciar sesión")
    @Column(length = 100)
    @NotBlank(message = "El nombre del ticket es obligatorio")
    private String nombre;
    

    @Schema(description = "Descripción del problema", example = "El sistema no reconoce mis credenciales.")
    @Column(length = 200)
    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;
    

     @Schema(description = "Tipo de problema", example = "Simple")
    @Column(length = 50)
    @NotBlank(message = "El tipo de problema del ticket es obligatorio")
    private String tipoProblema;
    

    @Schema(description = "Estado actual del ticket", example = "Abierto")
    @Column(length = 50)
    @NotBlank(message = "El estado del ticket es obligatorio")
    private String estadoTicket;


    @Schema(description = "ID del curso relacionado", example = "101")
    @NotNull(message = "id de curso es obligatorio")
    private int curso_adjunto;
    
    @Schema(description = "Técnico asignado al ticket")
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

}

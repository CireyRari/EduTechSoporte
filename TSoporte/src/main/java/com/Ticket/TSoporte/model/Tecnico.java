package com.Ticket.TSoporte.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del tecnico es obligatorio")
    @Column
    private String nombre;

    @NotBlank(message = "El cargo es obligatorio")
    @Column
    private String cargo;

}


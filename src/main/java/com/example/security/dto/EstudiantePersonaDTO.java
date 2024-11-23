package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiantePersonaDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String dni;
    private String telefono;
    private String codigo; // Código del estudiante
}

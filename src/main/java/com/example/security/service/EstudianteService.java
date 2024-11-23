package com.example.security.service;

import java.util.List;
import java.util.Optional;

import com.example.security.dto.EstudiantePersonaDTO; // Importa el DTO
import com.example.security.entity.Estudiante;

public interface EstudianteService {
    Estudiante create(Estudiante a);
    Estudiante update(Estudiante a);
    void delete(Long id);
    Optional<Estudiante> read(Long id);
    List<Estudiante> readAll();
    
    // Nuevo método para obtener los datos del estudiante
    EstudiantePersonaDTO obtenerDatosEstudiantePorUsuario(Long idUsuario);
}

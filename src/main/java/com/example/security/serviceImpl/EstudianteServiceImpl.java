package com.example.security.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dto.EstudiantePersonaDTO;
import com.example.security.entity.Estudiante;
import com.example.security.entity.Persona;
import com.example.security.entity.Usuario;
import com.example.security.repository.EstudianteRepository;
import com.example.security.repository.PersonaRepository;
import com.example.security.repository.UsuarioRepository;
import com.example.security.service.EstudianteService;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Estudiante create(Estudiante estudiante) {
        if (estudiante.getPersona() != null && estudiante.getPersona().getId() != null) {
            Persona persona = personaRepository.findById(estudiante.getPersona().getId())
                               .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + estudiante.getPersona().getId()));
            estudiante.setPersona(persona);
        } else {
            throw new RuntimeException("ID de Persona es requerido para crear Estudiante");
        }

        return repository.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante) {
        if (estudiante.getPersona() != null && estudiante.getPersona().getId() != null) {
            Persona persona = personaRepository.findById(estudiante.getPersona().getId())
                               .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + estudiante.getPersona().getId()));
            estudiante.setPersona(persona);
        }
        return repository.save(estudiante);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Estudiante> read(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Estudiante> readAll() {
        return repository.findAll();
    }

    // Implementación del nuevo método
    @Override
    public EstudiantePersonaDTO obtenerDatosEstudiantePorUsuario(Long idUsuario) {
        // Buscar el usuario por id_usuario
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener la persona asociada al usuario
        Persona persona = usuario.getPersona();

        // Obtener el estudiante asociado a la persona
        Estudiante estudiante = repository.findByPersona(persona)
                .orElseThrow(() -> new RuntimeException("No se encontraron datos de estudiante"));

        // Construir y retornar el DTO
        return new EstudiantePersonaDTO(
                persona.getNombre(),
                persona.getApellido(),
                persona.getEmail(),
                persona.getDni(),
                persona.getTelefono(),
                estudiante.getCodigo() // Código del estudiante
        );
    }
}


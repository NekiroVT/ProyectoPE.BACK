package com.example.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.config.JwtTokenProvider;
import com.example.security.dto.EstudiantePersonaDTO;
import com.example.security.entity.Estudiante;
import com.example.security.service.EstudianteService;


@RestController
@RequestMapping("/api/estudiante")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Endpoint para leer todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> readAll() {
        List<Estudiante> lista = estudianteService.readAll();
        return lista.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Endpoint para crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante) {
        return new ResponseEntity<>(estudianteService.create(estudiante), HttpStatus.CREATED);
    }

    // Endpoint para leer un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> read(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.read(id);
        return estudiante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para actualizar un estudiante por ID
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        Optional<Estudiante> existingEstudiante = estudianteService.read(id);
        if (existingEstudiante.isPresent()) {
            return new ResponseEntity<>(estudianteService.update(estudiante), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un estudiante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        estudianteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Nuevo endpoint: Obtener datos combinados de Estudiante y Persona por id_usuario
    @GetMapping("/datos")
    public ResponseEntity<EstudiantePersonaDTO> obtenerDatosEstudiante(@RequestHeader("Authorization") String token) {
        // Extraer el token JWT del encabezado
        String jwtToken = token.replace("Bearer ", ""); // Remueve el prefijo "Bearer"

        // Obtener el id_usuario desde el token
        Long idUsuario = jwtTokenProvider.getUserId(jwtToken);

        // Usar el servicio para obtener los datos combinados
        EstudiantePersonaDTO datosEstudiante = estudianteService.obtenerDatosEstudiantePorUsuario(idUsuario);

        // Retornar los datos al frontend
        return ResponseEntity.ok(datosEstudiante);
    }
}
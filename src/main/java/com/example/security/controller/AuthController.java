package com.example.security.controller;

import com.example.security.dto.AuthResponseDto;
import com.example.security.dto.LoginDto;
import com.example.security.dto.RegisterDto;
import com.example.security.entity.Usuario;
import com.example.security.entity.Rol;
import com.example.security.repository.UsuarioRepository;
import com.example.security.repository.RolRepository;
import com.example.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	@Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto); // Generar el token JWT
        Usuario usuario = usuarioRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener el primer rol del usuario
        String rol = usuario.getUsuarioRoles().stream()
                .map(usuarioRol -> usuarioRol.getRol().getName())
                .findFirst()
                .orElse("USER");

        // Construir la respuesta incluyendo el id_usuario
        AuthResponseDto authResponseDto = new AuthResponseDto(
                token, // Token de acceso
                "Hola " + rol, // Mensaje de bienvenida
                usuario.getUsername(), // Nombre de usuario
                rol, // Rol del usuario
                usuario.getId() // ID del usuario
        );

        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

}
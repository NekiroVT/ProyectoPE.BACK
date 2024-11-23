package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String accessToken; // Token JWT
    private String welcomeMessage; // Mensaje de bienvenida
    private String username; // Nombre de usuario
    private String rol; // Rol del usuario
    private Long id_usuario; // Cambiado de userId a id_usuario
}
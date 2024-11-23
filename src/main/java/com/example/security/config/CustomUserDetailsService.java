package com.example.security.config;


import java.util.Set;
import java.util.stream.Collectors;

import com.example.security.entity.Usuario;
import com.example.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        // Modificar para obtener los roles a través de Usuario_Rol
        Set<GrantedAuthority> authorities = user.getUsuarioRoles().stream()
                .map(usuarioRol -> new SimpleGrantedAuthority("ROLE_" + usuarioRol.getRol().getName()))
                .collect(Collectors.toSet());

        // Retornar un objeto CustomUserDetails en lugar del genérico User
        return new CustomUserDetails(
                user.getId(), // Aquí pasas el id_usuario
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}

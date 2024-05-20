package com.rubejuca.proyectointegrador.services;

import java.util.List;
import java.util.Optional;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.rubejuca.proyectointegrador.controllers.usuarios.ActualizarUsuarioDto;
import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.respositories.UsuarioRepository;

@Service
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El usuario %s ya existe".formatted(usuario.getEmail()));
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario update(ActualizarUsuarioDto dto) {
        Usuario usuario = findById(dto.getUserId());
        usuario.setRol(dto.getRol());
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(String id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario %s no existe".formatted(id)));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("El usuario %s no existe".formatted(email)));
    }

}

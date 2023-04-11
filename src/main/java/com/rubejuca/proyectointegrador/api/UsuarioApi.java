package com.rubejuca.proyectointegrador.api;


import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.respositories.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioApi {

  @Autowired
  UsuarioRespository usuarioRespository;

  @PostMapping("/api/usuarios")
  public Usuario crear(@RequestBody Usuario usuario) {
    return usuarioRespository.save(usuario);
  }

  @GetMapping("/api/usuarios")
  public List<Usuario> leer() {
    return usuarioRespository.findAll();
  }

  @GetMapping("/api/usuarios/{id}")
  public Usuario leer(@PathVariable("id") String id) {
    return usuarioRespository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
  }

  @PutMapping("/api/usuarios/{id}")
  public Usuario crear(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
    return usuarioRespository.save(usuario);
  }

}

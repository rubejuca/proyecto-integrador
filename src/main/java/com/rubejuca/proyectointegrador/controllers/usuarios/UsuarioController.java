package com.rubejuca.proyectointegrador.controllers.usuarios;

import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.model.types.Permisos;
import com.rubejuca.proyectointegrador.services.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Usuario API",
    description = "Permite gestionar los usuarios"
)

@RestController
@Slf4j
public class UsuarioController {

  @Autowired
  UsuarioService usuarioService;

  @PostMapping("/api/usuarios")
  public Usuario create(@RequestBody Usuario usuario) {
    return usuarioService.create(usuario);
  }

  @GetMapping("/api/usuarios")
  public List<Usuario> findAll() {
    return usuarioService.findAll();
  }

  @GetMapping("/api/usuarios/{id}")
  public Usuario find(@PathVariable("id") String id) {
    return usuarioService.findById(id);
  }

  @PutMapping("/api/usuarios/{id}")
  public Usuario update(@PathVariable("id") String id, @RequestBody ActualizarUsuarioDto dto) {
    return usuarioService.update(dto.toBuilder().userId(id).build());
  }

  @GetMapping("/api/usuarios/permisos")
  public List<Permisos> findPermisos(@RequestParam("email") String email) {
    log.info("email: {}", email);
    Usuario usuario = usuarioService.findByEmail(email);
    return usuario.getRol().getPermisos();
  }

}

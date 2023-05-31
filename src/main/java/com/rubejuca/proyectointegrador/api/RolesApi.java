package com.rubejuca.proyectointegrador.api;

import com.rubejuca.proyectointegrador.model.types.Rol;

import org.springframework.web.bind.annotation.*;

@RestController
public class RolesApi {

  @GetMapping("/api/roles")
  public Rol[] findAll() {
    return Rol.values();
  }

}

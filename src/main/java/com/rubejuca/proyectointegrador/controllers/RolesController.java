package com.rubejuca.proyectointegrador.controllers;

import com.rubejuca.proyectointegrador.model.types.Rol;

import org.springframework.web.bind.annotation.*;

@RestController
public class RolesController {

  @GetMapping("/api/roles")
  public Rol[] findAll() {
    return Rol.values();
  }

}

package com.rubejuca.proyectointegrador.controllers;

import com.rubejuca.proyectointegrador.model.types.Rol;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "Roles API",
    description = "Permite gestionar las roles de los usuarios"
)

@RestController
public class RolesController {

  @GetMapping("/api/roles")
  public Rol[] findAll() {
    return Rol.values();
  }

}

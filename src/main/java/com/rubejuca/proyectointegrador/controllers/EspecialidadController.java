package com.rubejuca.proyectointegrador.controllers;


import com.rubejuca.proyectointegrador.model.entity.Especialidad;
import com.rubejuca.proyectointegrador.respositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EspecialidadController {

  @Autowired
  EspecialidadRepository especialidadRepository;

  @PostMapping("/api/especialidades")
  public Especialidad crear(@RequestBody Especialidad especialidad) {
    return especialidadRepository.save(especialidad);
  }

  @GetMapping("/api/especialidades")
  public List<Especialidad> leer() {
    return especialidadRepository.findAll();
  }

  @GetMapping("/api/especialidades/{id}")
  public Especialidad leer(@PathVariable("id") Long id) {
    return especialidadRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Especialidad no existe"));
  }

  @PutMapping("/api/especialidades/{id}")
  public Especialidad crear(@PathVariable("id") Long id, @RequestBody Especialidad especialidad) {
    return especialidadRepository.save(especialidad);
  }

}

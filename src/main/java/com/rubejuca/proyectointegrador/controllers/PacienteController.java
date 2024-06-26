package com.rubejuca.proyectointegrador.controllers;

import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.services.PacienteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Paciente API",
    description = "Permite gestionar los pacientes"
)

@RestController
@Slf4j
public class PacienteController {

  @Autowired
  PacienteService pacienteService;

  @PostMapping("/api/pacientes")
  @ResponseStatus(HttpStatus.CREATED)
  public Paciente crear(@RequestBody Paciente paciente) {
    return pacienteService.create(paciente);
  }

  @GetMapping("/api/pacientes")
  public List<Paciente> leer() {
    return pacienteService.readAll();
  }

  @GetMapping("/api/pacientes/{id}")
  public Paciente leer(@PathVariable("id") String id) {
    return pacienteService.read(id);
  }

  @PutMapping("/api/pacientes/{id}")
  public Paciente actualizar(@PathVariable("id") String id, @RequestBody Paciente paciente) {
    log.info("Paciente: {}", paciente);
    return pacienteService.update(paciente);
  }

}

package com.rubejuca.proyectointegrador.api;

import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.services.PacienteService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PacienteApi {

  @Autowired
  PacienteService pacienteService;

  @PostMapping("/api/pacientes")
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

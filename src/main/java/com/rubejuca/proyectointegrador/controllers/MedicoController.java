package com.rubejuca.proyectointegrador.controllers;

import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.services.MedicoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Medico API",
    description = "Permite gestionar los medicos"
)
@RestController
public class MedicoController {

  @Autowired
  MedicoService medicoService;

  @PostMapping("/api/medicos")
  @ResponseStatus(HttpStatus.CREATED)
  public Medico crear(@RequestBody Medico medico) {
    return medicoService.create(medico);
  }

  @GetMapping("/api/medicos")
  public List<Medico> leer() {
    return medicoService.readAll();
  }

  @GetMapping("/api/medicos/search")
  public List<Medico> buscar(@RequestParam("query") String value) {
    return medicoService.search(value);
  }

  @GetMapping("/api/medicos/{id}")
  public Medico leer(@PathVariable("id") String id) {
    return medicoService.read(id);
  }

  @PutMapping("/api/medicos/{id}")
  public Medico actualizar(@PathVariable("id") String id, @RequestBody Medico medico) {
    return medicoService.update(medico);
  }

}

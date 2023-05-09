package com.rubejuca.proyectointegrador.api;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.services.CitaService;
import com.rubejuca.proyectointegrador.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitaApi {

    @Autowired
    CitaService citaService;

    @PostMapping("/api/citas")
    public Cita crear(@RequestBody Cita cita) {
        return citaService.create(cita);
    }

    @GetMapping("/api/citas")
    public List<Cita> leer() {
        return citaService.readAll();
    }





}
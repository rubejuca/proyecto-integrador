package com.rubejuca.proyectointegrador.api.citas;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CitaApi {

    @Autowired
    CitaService citaService;

    @PostMapping("/api/citas")
    public CitaDto crear(@RequestBody CrearCitaDto dto) {
        Cita cita = CitasMapper.toCita(dto);
        Cita citaCreada = citaService.create(cita);
        return CitasMapper.toDto(citaCreada);
    }

    @GetMapping("/api/citas")
    public List<CitaInfo> leer() {
        return citaService.readAll();
    }

}
package com.rubejuca.proyectointegrador.controllers.citas;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.services.CitaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
    name = "Cita API",
    description = "Permite gestionar las citas"
)

@RestController
public class CitaController {

    @Autowired
    CitaService citaService;

    @PostMapping("/api/citas")
    @ResponseStatus(HttpStatus.CREATED)
    public CitaDto crear(@RequestBody CrearCitaDto dto) {
        Cita cita = CitasMapper.toCita(dto);
        Cita citaCreada = citaService.create(cita);
        return CitasMapper.toDto(citaCreada);
    }

    @GetMapping("/api/citas")
    public List<CitaInfo> leer() {
        return citaService.readAll();
    }

    @GetMapping("/api/citas/{citaId}")
    public CitaInfo leer(@PathVariable("citaId") String citaId) {
        return citaService.findById(citaId);
    }

    @PutMapping("/api/citas/{citaId}")
    public Cita actualizar(@PathVariable("citaId") String citaId, @RequestBody ActualizarCitaDto dto) {
        return citaService.update(citaId, dto);
    }

    @PutMapping("/api/citas/{citaId}/atender")
    public Cita atender(@PathVariable("citaId") String citaId, @RequestBody AtencionCitaDto dto) {
        return citaService.atender(citaId, dto.diagnostico());
    }

}
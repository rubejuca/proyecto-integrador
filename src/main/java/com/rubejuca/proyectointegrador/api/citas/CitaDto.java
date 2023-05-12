package com.rubejuca.proyectointegrador.api.citas;

import java.time.LocalDateTime;

import com.rubejuca.proyectointegrador.model.entity.Cita.CitaBuilder;
import com.rubejuca.proyectointegrador.model.types.EstadoCitas;

import lombok.Builder;

@Builder
public record CitaDto(
        String id,
        LocalDateTime fechaHora,
        String pacienteId,
        String medicoId,
        String motivo,
        EstadoCitas estado,
        LocalDateTime fechaCreacion) {

}

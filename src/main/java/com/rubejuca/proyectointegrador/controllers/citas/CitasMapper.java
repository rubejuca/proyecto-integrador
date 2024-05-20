package com.rubejuca.proyectointegrador.controllers.citas;

import java.time.LocalDateTime;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.types.EstadoCitas;

public class CitasMapper {

    public static Cita toCita(CrearCitaDto dto) {
        return Cita.builder()
                .fechaHora(dto.fechaHora())
                .pacienteId(dto.pacienteId())
                .medicoId(dto.medicoId())
                .motivo(dto.motivo())
                .estado(EstadoCitas.PROGRAMADA)
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    public static CitaDto toDto(Cita cita) {
        return CitaDto.builder()
                .id(cita.getId())
                .fechaHora(cita.getFechaHora())
                .medicoId(cita.getMedicoId())
                .pacienteId(cita.getPacienteId())
                .motivo(cita.getMotivo())
                .estado(cita.getEstado())
                .fechaCreacion(cita.getFechaCreacion())
                .build();
    }

}

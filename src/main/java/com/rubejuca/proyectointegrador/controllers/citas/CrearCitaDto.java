package com.rubejuca.proyectointegrador.controllers.citas;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record CrearCitaDto(
        LocalDateTime fechaHora,
        String pacienteId,
        String medicoId,
        String motivo) {
}

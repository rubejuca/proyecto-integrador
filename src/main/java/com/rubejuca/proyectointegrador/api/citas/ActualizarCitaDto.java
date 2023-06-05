package com.rubejuca.proyectointegrador.api.citas;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ActualizarCitaDto(
        LocalDateTime fechaHora,
        String medicoId,
        String motivo) {
}

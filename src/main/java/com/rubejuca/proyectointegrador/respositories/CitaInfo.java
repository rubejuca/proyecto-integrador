package com.rubejuca.proyectointegrador.respositories;

import java.time.LocalDateTime;

public interface CitaInfo {

    String getId();

    LocalDateTime getFechaHora();

    String getPacienteId();

    String getPacienteNombre();

    String getMedicoId();

    String getMedicoNombre();

    String getEstado();
}

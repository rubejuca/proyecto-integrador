package com.rubejuca.proyectointegrador.respositories;

import com.rubejuca.proyectointegrador.model.entity.Cita;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitaRepository extends JpaRepository<Cita, String> {

    @Query("""
                SELECT
                    c.id AS id,
                    c.fechaHora AS fechaHora,
                    c.medicoId AS medicoId,
                    c.pacienteId AS pacienteId,
                    c.estado AS estado,
                    CONCAT(m.apellidos, ', ', m.nombres) AS medicoNombre,
                    CONCAT(p.apellidos, ', ', p.nombres) AS pacienteNombre
                FROM Cita c, Medico m, Paciente p
                WHERE c.medicoId = m.id
                  AND c.pacienteId = p.id
                ORDER BY c.fechaHora DESC
            """)
    List<CitaInfo> findCitas();

    @Query("""
                SELECT
                    c.id AS id,
                    c.fechaHora AS fechaHora,
                    c.medicoId AS medicoId,
                    c.pacienteId AS pacienteId,
                    c.estado AS estado,
                    c.motivo AS motivo,
                    c.diagnostico AS diagnostico,
                    CONCAT(m.apellidos, ', ', m.nombres) AS medicoNombre,
                    CONCAT(p.apellidos, ', ', p.nombres) AS pacienteNombre
                FROM Cita c, Medico m, Paciente p
                WHERE c.id = :citaId
                  AND c.medicoId = m.id
                  AND c.pacienteId = p.id
                ORDER BY c.fechaHora DESC
            """)
    Optional<CitaInfo> findCitaById(String citaId);

    boolean existsByMedicoIdAndFechaHora(String medicoId, LocalDateTime fechaHora);

    boolean existsByPacienteIdAndFechaHora(String pacienteId, LocalDateTime fechaHora);

}

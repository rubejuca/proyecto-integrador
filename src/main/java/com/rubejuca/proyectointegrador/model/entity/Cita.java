package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.EstadoCitas;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static com.rubejuca.proyectointegrador.services.ValidationService.isNotEmpty;
import static com.rubejuca.proyectointegrador.services.ValidationService.notNull;

@Builder
@Getter
@NoArgsConstructor
@Entity
@Table(name = "CITA")
public class Cita {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "paciente_id", nullable = false, length = 36)
    private String pacienteId;

    @Column(name = "medico_id", nullable = false, length = 36)
    private String medicoId;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "motivo", length = 1000)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCitas estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_atencion")
    private LocalDateTime fechaAtencion;

    @Column(name = "diagnostico", length = 2000)
    private String diagnostico;

    public Cita(String id, String pacienteId, String medicoId, LocalDateTime fechaHora, String motivo,
            EstadoCitas estado, LocalDateTime fechaCreacion, LocalDateTime fechaAtencion, String diagnostico) {
        this.id = id;
        this.pacienteId = isNotEmpty(pacienteId, "Debe ingresar un Paciente");
        this.medicoId = isNotEmpty(medicoId, "Debe ingresar un Médico");
        this.fechaHora = validateFechaHora(fechaHora);
        this.motivo = motivo;
        this.estado = notNull(estado, "Debe tener un estado valido");
        this.fechaCreacion = notNull(fechaCreacion, "Debe tener fecha de creación");
        this.fechaAtencion = fechaAtencion;
        this.diagnostico = diagnostico;
    }

    public static LocalDateTime validateFechaHora(LocalDateTime fechaHora) {
        Assert.notNull(fechaHora, "Debe ingresar fecha y hora de la cita");
        Assert.isTrue(fechaHora.isAfter(LocalDateTime.now()),
                "La fecha de la cita debe ser posterior a la fecha actual");

        var minFechaHora = fechaHora.withHour(5).withMinute(59).withSecond(59).withNano(9999);
        var maxFechaHora = fechaHora.withHour(18).withMinute(0).withSecond(0).withNano(9999);
        Assert.isTrue(fechaHora.isAfter(minFechaHora) && fechaHora.isBefore(maxFechaHora),
                "La Hora de la cita debe ser entre las 6am y 6pm");
        return fechaHora;
    }

    // Esto evita tener un modelo anémico: Modelo anémico es cuando la responsabilidad del modelo
    // no la tiene el modelo sino otras capas
    public void atender(String diagnostico) {
        this.fechaAtencion = LocalDateTime.now();
        this.diagnostico = diagnostico;
        this.estado = EstadoCitas.ATENDIDA;
    }

    // Esto evita tener un modelo anémico
    public void actualizar(LocalDateTime fechaHora, String medicoId, String motivo) {
        this.fechaHora = validateFechaHora(fechaHora);
        this.medicoId = isNotEmpty(medicoId, "Debe ingresar un Médico");
        this.motivo = motivo;
    }

}
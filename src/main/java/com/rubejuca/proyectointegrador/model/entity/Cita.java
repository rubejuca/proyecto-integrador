package com.rubejuca.proyectointegrador.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CITA")
public class Cita {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "paciente_id", nullable = false)
    private String pacienteId;

    @Column(name = "medico_id", nullable = false)
    private String medicoId;

    @Column(name = "fecha_hora",  nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "motivo" )
    private String motivo;

}
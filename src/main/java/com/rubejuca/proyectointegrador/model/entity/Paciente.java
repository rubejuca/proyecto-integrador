package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Sexo;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "PACIENTE")
public class Paciente {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "documento", nullable = false, length = 20)
  private String documento;

  @Column(name = "tipo_documento", nullable = false, length = 5)
  @Enumerated(EnumType.STRING)
  private TipoDocumento tipoDocumento;

  @Column(name = "nombres", nullable = false)
  private String nombres;

  @Column(name = "apellidos", nullable = false)
  private String apellidos;

  @Column(name = "sexo", nullable = false)
  @Enumerated(EnumType.STRING)
  private Sexo sexo;

  @Column(name = "email")
  private String email;

  @Column(name = "telefono", nullable = false)
  private String telefono;

  @Column(name = "fecha_nacimiento", nullable = false)
  private LocalDate fechaNacimiento;

  @Column(name = "ciudad")
  private String ciudad;

  @Column(name = "direccion")
  private String direccion;

}

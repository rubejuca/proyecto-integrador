package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Rol;
import lombok.Data;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "email", nullable = false, length = 100, unique = true)
  private String email;

  @Column(name = "rol", nullable = false, length = 20)
  @Enumerated(EnumType.STRING)
  private Rol rol;

  @Column(name = "medicoId", length = 36)
  private String medicoId;

}

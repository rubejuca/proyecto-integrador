package com.rubejuca.proyectointegrador.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "ESPECIALIDAD")
public class Especialidad {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "nombre", nullable = false)
  private String nombre;

}

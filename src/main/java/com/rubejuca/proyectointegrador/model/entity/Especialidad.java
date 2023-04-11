package com.rubejuca.proyectointegrador.model.entity;

import lombok.Data;

import javax.persistence.*;

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

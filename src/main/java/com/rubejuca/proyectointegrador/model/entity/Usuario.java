package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Rol;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

  @Id
  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "rol", nullable = false)
  @Enumerated(EnumType.STRING)
  private Rol rol;

}

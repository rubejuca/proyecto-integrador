package com.rubejuca.proyectointegrador.model.types;

public enum Sexo {
  M("Masculino"),
  F("Femenino"),
  ;

  private final String nombre;

  Sexo(String nombre) {
    this.nombre = nombre;
  }
}

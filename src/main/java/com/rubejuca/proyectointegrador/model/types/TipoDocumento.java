package com.rubejuca.proyectointegrador.model.types;

public enum TipoDocumento {

  TI("Tarjeta de Identidad"),
  NUIP("Número de Identificación Personal"),
  RC("Registro Civil"),
  CC("Cédula de Ciudadanía"),
  CE("Cédula de Extranjería"),
  PP("Pasaporte");

  private String descripcion;

  TipoDocumento(String descripcion) {
    this.descripcion = descripcion;
  }
}

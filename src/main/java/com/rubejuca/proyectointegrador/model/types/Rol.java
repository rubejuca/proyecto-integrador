package com.rubejuca.proyectointegrador.model.types;

import java.util.List;

public enum Rol {

  ADMIN(List.of(Permisos.MENU_ATENCION, Permisos.MENU_CITAS, Permisos.MENU_MEDICOS, Permisos.MENU_PACIENTES,
      Permisos.MENU_USUARIOS)),
  RECEPCIONISTA(List.of(Permisos.MENU_CITAS, Permisos.MENU_PACIENTES, Permisos.MENU_MEDICOS)),
  MEDICO(List.of(Permisos.MENU_ATENCION));

  private final List<Permisos> permisos;

  private Rol(List<Permisos> permisos) {
    this.permisos = permisos;
  }

  public List<Permisos> getPermisos() {
    return permisos;
  }

}
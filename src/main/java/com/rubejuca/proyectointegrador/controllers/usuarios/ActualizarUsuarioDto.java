package com.rubejuca.proyectointegrador.controllers.usuarios;

import com.rubejuca.proyectointegrador.model.types.Rol;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class ActualizarUsuarioDto {

    public final String userId;
    public final Rol rol;

}

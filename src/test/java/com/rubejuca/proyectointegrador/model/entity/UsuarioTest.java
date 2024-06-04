package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Rol;
import com.rubejuca.proyectointegrador.model.types.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {


  @Test
  public void testUsuarioEqualsHashCode() {
    Usuario usuario1 = Usuario.builder()
        .id("1")
        .email("danna@.com")
        .rol(Rol.RECEPCIONISTA)
        .medicoId("12345")
        .build();

    Usuario usuario2 = Usuario.builder()
        .id("1")
        .email("danna@.com")
        .rol(Rol.RECEPCIONISTA)
        .medicoId("12345")
        .build();

    Assertions.assertEquals(usuario1, usuario2);
    Assertions.assertEquals(usuario1.hashCode(), usuario2.hashCode());

  }

  @Test
  public void testUsuarioNotEqualsHashCode() {
    Usuario usuario1 = Usuario.builder()
        .id("1")
        .email("danna@.com")
        .rol(Rol.RECEPCIONISTA)
        .medicoId("12345")
        .build();

    Usuario usuario2 = Usuario.builder()
        .id("2")
        .email("danna@.com")
        .rol(Rol.RECEPCIONISTA)
        .medicoId("123456")
        .build();

    Assertions.assertNotEquals(usuario1, usuario2);
    Assertions.assertNotEquals(usuario1.hashCode(), usuario2.hashCode());

  }

}

package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Sexo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PacienteTest {

  @Test
  public void testPacienteEqualsHashCode() {
    Paciente paciente1 = Paciente.builder()
        .id("1")
        .documento("12345")
        .apellidos("Rojas")
        .sexo(Sexo.M)
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .fechaNacimiento(LocalDate.of(2003, 1, 1))
        .ciudad("Bogota")
        .direccion("calle 123")
        .build();

    Paciente paciente2 = Paciente.builder()
        .id("1")
        .documento("12345")
        .apellidos("Rojas")
        .sexo(Sexo.M)
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .fechaNacimiento(LocalDate.of(2003, 1, 1))
        .ciudad("Bogota")
        .direccion("calle 123")
        .build();

    Assertions.assertEquals(paciente1, paciente2);
    Assertions.assertEquals(paciente1.hashCode(), paciente2.hashCode());

  }

  @Test
  public void testPacienteNotEqualsHashCode() {
    Paciente paciente1 = Paciente.builder()
        .id("1")
        .documento("12345")
        .apellidos("Rojas")
        .sexo(Sexo.M)
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .fechaNacimiento(LocalDate.of(2003, 1, 1))
        .ciudad("Bogota")
        .direccion("calle 123")
        .build();

    Paciente paciente2 = Paciente.builder()
        .id("1")
        .documento("12345")
        .apellidos("Rojas")
        .sexo(Sexo.M)
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .fechaNacimiento(LocalDate.of(2003, 1, 1))
        .ciudad("Bogota")
        .direccion("otra direccion diferente")
        .build();

    Assertions.assertNotEquals(paciente1, paciente2);
    Assertions.assertNotEquals(paciente1.hashCode(), paciente2.hashCode());

  }

}

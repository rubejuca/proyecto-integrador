package com.rubejuca.proyectointegrador.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EspecialidadTest {
  @Test
  public void testEspecialidadEqualsHashCode() {

    Especialidad especialidad1 = Especialidad.builder()
        .id(1L)
        .nombre("Cardiologo")
        .build();

    Especialidad especialidad2 = Especialidad.builder()
        .id(1L)
        .nombre("Cardiologo")
        .build();

    Assertions.assertEquals(especialidad1, especialidad2);
    Assertions.assertEquals(especialidad1.hashCode(), especialidad2.hashCode());

  }

  @Test
  public void testEspecialidadNotEqualsHashCode() {

    Especialidad especialidad1 = Especialidad.builder()
        .id(1L)
        .nombre("Cardiologo")
        .build();

    Especialidad especialidad2 = Especialidad.builder()
        .id(2L)
        .nombre("Cardiologo")
        .build();

    Assertions.assertNotEquals(especialidad1, especialidad2);
    Assertions.assertNotEquals(especialidad1.hashCode(), especialidad2.hashCode());
  }


}

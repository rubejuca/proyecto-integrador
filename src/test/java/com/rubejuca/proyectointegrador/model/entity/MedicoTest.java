package com.rubejuca.proyectointegrador.model.entity;

import com.rubejuca.proyectointegrador.model.types.Rol;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicoTest {

  @Test
  public void testMedicoEqualsHashCode() {
    Medico medico1 = Medico.builder()
        .id("1")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Camilo")
        .apellidos("Rubio")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("calle 123")
        .especialidad("Cardiologo")
        .build();

    Medico medico2 = Medico.builder()
        .id("1")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Camilo")
        .apellidos("Rubio")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("calle 123")
        .especialidad("Cardiologo")
        .build();


    Assertions.assertEquals(medico1, medico2);
    Assertions.assertEquals(medico1.hashCode(), medico2.hashCode());

  }

  @Test
  public void testUsuarioNotEqualsHashCode() {
    Medico medico1 = Medico.builder()
        .id("1")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Camilo")
        .apellidos("Rubio")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("calle 123")
        .especialidad("Cardiologo")
        .build();

    Medico medico2 = Medico.builder()
        .id("2")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Camilo")
        .apellidos("Rubio")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("calle 123")
        .especialidad("Cardiologo")
        .build();

    Assertions.assertNotEquals(medico1, medico2);
    Assertions.assertNotEquals(medico1.hashCode(), medico2.hashCode());

  }
}

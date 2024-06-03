package com.rubejuca.proyectointegrador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import com.rubejuca.proyectointegrador.services.MedicoService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MedicoController.class})
public class MedicoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MedicoService medicoService;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void testCreate() throws Exception {

    Medico medico = Medico.builder()
        .id("123")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Danna")
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("carrera 123")
        .especialidad("Cardiologo")
        .build();

    when(medicoService.create(medico))
        .thenReturn(medico);

    mvc.perform(post("/api/medicos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated());
  }

  @Test
  public void testFailCreate() throws Exception {

    Medico medico = Medico.builder()
        .id("123")
        .documento("12345")
        .tipoDocumento(TipoDocumento.TI)
        .nombres("Danna")
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("carrera 123")
        .especialidad("Cardiologo")
        .build();

    when(medicoService.create(medico))
        .thenThrow(new IllegalArgumentException("El medico ya existe"));

    mvc.perform(post("/api/medicos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isBadRequest());
  }

}

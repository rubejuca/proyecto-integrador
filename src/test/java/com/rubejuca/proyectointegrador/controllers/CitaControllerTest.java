package com.rubejuca.proyectointegrador.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rubejuca.proyectointegrador.controllers.citas.CitaController;
import com.rubejuca.proyectointegrador.controllers.citas.CitaDto;
import com.rubejuca.proyectointegrador.controllers.citas.CitasMapper;
import com.rubejuca.proyectointegrador.controllers.citas.CrearCitaDto;
import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.types.EstadoCitas;
import com.rubejuca.proyectointegrador.services.CitaService;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CitaController.class)
public class CitaControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CitaService citaService;

  private ObjectMapper objectMapper = JsonMapper
      .builder()
      .addModule(new Jdk8Module())
      .addModule(new JavaTimeModule())
      .build();

  @Test
  public void testCrearCitaExitosamente() throws Exception {


    CrearCitaDto dto = CrearCitaDto.builder()
        .fechaHora(LocalDateTime.of(2050, 3, 31, 14, 30))
        .motivo("motivo")
        .pacienteId("123")
        .medicoId("123")
        .build();

    when(citaService.create(Mockito.any()))
        .thenAnswer(AdditionalAnswers.returnsFirstArg());

    mvc.perform(post("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isCreated());




  }




}

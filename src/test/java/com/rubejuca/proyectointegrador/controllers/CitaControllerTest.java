package com.rubejuca.proyectointegrador.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.JsonPath;
import com.rubejuca.proyectointegrador.controllers.citas.*;
import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.types.EstadoCitas;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.services.CitaService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

  private CitaInfo crearCitaInfo() {
    return new CitaInfo() {

      @Override
      public String getId() {
        return "123";
      }

      @Override
      public LocalDateTime getFechaHora() {
        return LocalDateTime.now();
      }

      @Override
      public String getPacienteId() {
        return "1234";
      }

      @Override
      public String getPacienteNombre() {
        return "Paciente";
      }

      @Override
      public String getMedicoId() {
        return "12345";
      }

      @Override
      public String getMedicoNombre() {
        return "Medico";
      }

      @Override
      public String getEstado() {
        return "";
      }

      @Override
      public String getMotivo() {
        return "";
      }

      @Override
      public String getDiagnostico() {
        return "";
      }
    };
  }

  @Test
  public void testLeerCitaExitosamente() throws Exception {


    when(citaService.readAll())
        .thenReturn(List.of((crearCitaInfo())));

    mvc.perform(get("/api/citas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(List.of(crearCitaInfo()))))
        .andExpect(status().isOk());
  }

  @Test
  public void testLeerCitaPorIdExitosamente() throws Exception {

    when(citaService.findById("123"))
        .thenReturn(crearCitaInfo());

    mvc.perform(get("/api/citas/123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(crearCitaInfo())))
        .andExpect(status().isOk());
  }

  @Test
  public void testLeerCitaPorIdNoExiste() throws Exception {

    when(citaService.findById("123"))
        .thenThrow(new IllegalArgumentException ("La cita 123 no existe"));

    mvc.perform(get("/api/citas/123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(crearCitaInfo())))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("La cita 123 no existe")));

  }

  @Test
  public void testActualizarCitaExitosamente() throws Exception {

    ActualizarCitaDto dto = ActualizarCitaDto.builder()
        .fechaHora(LocalDateTime.of(2050, 3, 31, 14, 30))
        .medicoId("123456")
        .motivo("motivo actualizado")
        .build();

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .build();

    when(citaService.update("123", dto))
        .thenReturn(cita);

    mvc.perform(put("/api/citas/123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk());
  }


  @Test
  public void testActualizarCitaNoExiste() throws Exception {

    ActualizarCitaDto dto = ActualizarCitaDto.builder()
        .fechaHora(LocalDateTime.of(2050, 3, 31, 14, 30))
        .medicoId("123456")
        .motivo("motivo actualizado")
        .build();

    when(citaService.update("123", dto))
        .thenThrow(new EntityNotFoundException("La cita con id: 123 no existe"));

    mvc.perform(put("/api/citas/123")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("La cita con id: 123 no existe")));
  }

  @Test
  public void testAtencionCitaExitosamente() throws Exception {

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .build();

    when(citaService.atender("123", "diagnostico"))
        .thenReturn(cita);

    mvc.perform(put("/api/citas/123/atender")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cita)))
        .andExpect(status().isOk());
  }


  @Test
  public void testAtencionCitaNoExiste() throws Exception {

    AtencionCitaDto dto = new AtencionCitaDto("diagnostico");



    when(citaService.atender("123", "diagnostico"))
        .thenThrow(new EntityNotFoundException("La cita 123 no existe"));

    mvc.perform(put("/api/citas/123/atender")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("La cita 123 no existe")));
  }



}

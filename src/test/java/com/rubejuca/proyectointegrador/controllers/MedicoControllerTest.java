package com.rubejuca.proyectointegrador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import com.rubejuca.proyectointegrador.services.MedicoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {MedicoController.class})
public class MedicoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MedicoService medicoService;

  private ObjectMapper objectMapper = JsonMapper
          .builder()
          .addModule(new Jdk8Module())
          .addModule(new JavaTimeModule())
          .build();

  @Test
  public void testCrearMedicoExitosamente() throws Exception {

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
  public void testFallaCrearMedicoPorqueYaExiste() throws Exception {

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
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message", is("El medico ya existe")));
  }

  @Test
  public void testLeerMedicos() throws Exception {

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

    when(medicoService.readAll())
        .thenReturn(List.of(medico));

    mvc.perform(get("/api/medicos"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(1)))
        .andExpect(jsonPath("$[0].id", is("123")))
        .andExpect(jsonPath("$[0].documento", is("12345")))
        .andExpect(jsonPath("$[0].tipoDocumento", is("TI")))
        .andExpect(jsonPath("$[0].nombres", is("Danna")))
        .andExpect(jsonPath("$[0].apellidos", is("Rojas")))
        .andExpect(jsonPath("$[0].email", is("danna@.com")))
        .andExpect(jsonPath("$[0].telefono", is("3125851410")))
        .andExpect(jsonPath("$[0].direccion", is("carrera 123")))
        .andExpect(jsonPath("$[0].especialidad", is("Cardiologo")));

  }

  @Test
  public void testBuscarMedicos() throws Exception {

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
    when(medicoService.search("12345"))
        .thenReturn(List.of(medico));
    mvc.perform(get("/api/medicos/search").param("query", "12345"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(1)))
            .andExpect(jsonPath("$[0].id", is("123")))
            .andExpect(jsonPath("$[0].documento", is("12345")))
            .andExpect(jsonPath("$[0].nombres", is("Danna")))
            .andExpect(jsonPath("$[0].apellidos", is("Rojas")))
            .andExpect(jsonPath("$[0].email", is("danna@.com")))
            .andExpect(jsonPath("$[0].telefono", is("3125851410")))
            .andExpect(jsonPath("$[0].direccion", is("carrera 123")))
            .andExpect(jsonPath("$[0].especialidad", is("Cardiologo")));

  }
  @Test
  public void testLeerIdMedico() throws Exception {

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
    when(medicoService.read("123"))
        .thenReturn(medico);
    mvc.perform(get("/api/medicos/123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is("123")))
        .andExpect(jsonPath("$.documento", is("12345")))
        .andExpect(jsonPath("$.nombres", is("Danna")))
        .andExpect(jsonPath("$.apellidos", is("Rojas")))
        .andExpect(jsonPath("$.email", is("danna@.com")))
        .andExpect(jsonPath("$.telefono", is("3125851410")))
        .andExpect(jsonPath("$.direccion", is("carrera 123")))
        .andExpect(jsonPath("$.especialidad", is("Cardiologo")));
  }

  @Test
  public void testLeerIdMedicoNoExiste() throws Exception {
   Medico medico = Medico.builder()
        .id("123")
        .documento("12345")
        .nombres("Danna")
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("carrera 123")
        .especialidad("Cardiologo")
        .build();
    when(medicoService.read("123"))
        .thenThrow(new EntityNotFoundException("El médico con id: 123 no existe"));
    mvc.perform(get("/api/medicos/123"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("El médico con id: 123 no existe")));
  }

  @Test
  public void testActualizarMedicos() throws Exception {
    Medico medico = Medico.builder()
        .id("123")
        .documento("12345")
        .nombres("Danna")
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("carrera 123")
        .especialidad("Cardiologo")
        .build();
    when(medicoService.update(medico))
        .thenReturn(medico);

    mvc.perform(put("/api/medicos/123")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is("123")))
        .andExpect(jsonPath("$.documento", is("12345")))
        .andExpect(jsonPath("$.nombres", is("Danna")))
        .andExpect(jsonPath("$.apellidos", is("Rojas")))
        .andExpect(jsonPath("$.email", is("danna@.com")))
        .andExpect(jsonPath("$.telefono", is("3125851410")))
        .andExpect(jsonPath("$.direccion", is("carrera 123")))
        .andExpect(jsonPath("$.especialidad", is("Cardiologo")));
  }

  @Test
  public void testFailActualizarMedico() throws Exception {
    Medico medico = Medico.builder()
        .id("123")
        .documento("12345")
        .nombres("Danna")
        .apellidos("Rojas")
        .email("danna@.com")
        .telefono("3125851410")
        .direccion("carrera 123")
        .especialidad("Cardiologo")
        .build();
    when(medicoService.update(medico))
        .thenThrow(new EntityNotFoundException("El médico con id: 123 no existe"));
    mvc.perform(put("/api/medicos/123")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message", is("El médico con id: 123 no existe")));
  }



}

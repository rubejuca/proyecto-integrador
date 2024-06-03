package com.rubejuca.proyectointegrador.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.model.types.Sexo;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import com.rubejuca.proyectointegrador.services.PacienteService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {PacienteController.class})
public class PacienteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PacienteService pacienteService;

    private ObjectMapper objectMapper = JsonMapper
            .builder()
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build();

    @Test
    public void testCrearPacienteExitosamente() throws Exception {

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.create(paciente))
                .thenReturn(paciente);

        mvc.perform(post("/api/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testFallaCrearPacientePorqueYaExiste() throws Exception {

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.create(paciente))
                .thenThrow(new IllegalArgumentException("El paciente ya existe"));

        mvc.perform(post("/api/pacientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("El paciente ya existe")));
    }

    @Test
    public void testLeerPacientes() throws Exception {
        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.readAll())
                .thenReturn(List.of(paciente));
        mvc.perform(get("/api/pacientes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("1")));

    }

    @Test
    public void testLeerPaciente() throws Exception {
        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.read("1"))
                .thenReturn(paciente);

        mvc.perform(get("/api/pacientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    @Test
    public void testLeerPacienteNoExiste() throws Exception {

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.read("1"))
                .thenThrow(new EntityNotFoundException("Paciente no encontrado"));

        mvc.perform(get("/api/pacientes/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Paciente no encontrado")));
    }
    @Test
    public void testUpdatePaciente() throws Exception {

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.update(paciente))
                .thenReturn(paciente);

        mvc.perform(put("/api/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")));
    }

    @Test
    public void testUpdatePacienteNoExiste() throws Exception {

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("12345")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .sexo(Sexo.M)
                .email("danna@.com")
                .telefono("3125851410")
                .fechaNacimiento(LocalDate.of(2003, 1, 1))
                .ciudad("Bogota")
                .direccion("calle 123")
                .build();

        when(pacienteService.update(paciente))
                .thenThrow(new EntityNotFoundException("Paciente no encontrado"));

        mvc.perform(put("/api/pacientes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Paciente no encontrado")));
    }
}

package com.rubejuca.proyectointegrador.controllers;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rubejuca.proyectointegrador.model.entity.Especialidad;
import com.rubejuca.proyectointegrador.respositories.EspecialidadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (controllers = {EspecialidadController.class})
public class EspecialidadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EspecialidadRepository especialidadRepository;

    private ObjectMapper objectMapper = JsonMapper
            .builder()
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build();

    @Test
    public void testCrearEspecialidadExitosamente() throws Exception {

        Especialidad especialidad = Especialidad.builder()
                .id(123L)
                .nombre("Cardiologo")
                .build();

        when(especialidadRepository.save(especialidad)).thenReturn(especialidad);

        mvc.perform(post("/api/especialidades")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(especialidad)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testListarEspecialidades() throws Exception {

        Especialidad especialidad = Especialidad.builder()
                .id(123L)
                .nombre("Cardiologo")
                .build();

        when(especialidadRepository.findAll()).thenReturn(List.of(especialidad));

        mvc.perform(get("/api/especialidades"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLeerEspecialidad() throws Exception {

        Especialidad especialidad = Especialidad.builder()
                .id(123L)
                .nombre("Cardiologo")
                .build();

        when(especialidadRepository.findById(123L)).thenReturn(java.util.Optional.of(especialidad));

        mvc.perform(get("/api/especialidades/123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLeerEspecialidadInexistente() throws Exception {

        Especialidad especialidad = Especialidad.builder()
                .id(123L)
                .nombre("Cardiologo")
                .build();
        when(especialidadRepository.findById(123L))
                .thenThrow(new IllegalArgumentException("Especialidad no existe"));

        mvc.perform(get("/api/especialidades/123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateEspecialidad() throws Exception {
    	Especialidad especialidad = Especialidad.builder()
                .id(123L)
                .nombre("Cardiologo")
                .build();
    	mvc.perform(put("/api/especialidades/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(especialidad)))
                .andExpect(status().isOk());
    }


}

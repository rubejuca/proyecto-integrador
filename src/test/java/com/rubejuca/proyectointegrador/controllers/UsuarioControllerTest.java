package com.rubejuca.proyectointegrador.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rubejuca.proyectointegrador.controllers.usuarios.ActualizarUsuarioDto;
import com.rubejuca.proyectointegrador.controllers.usuarios.UsuarioController;
import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.model.types.Permisos;
import com.rubejuca.proyectointegrador.model.types.Rol;
import com.rubejuca.proyectointegrador.services.UsuarioService;
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

@WebMvcTest(controllers = {UsuarioController.class})
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private UsuarioService usuarioService;

    private ObjectMapper objectMapper = JsonMapper
            .builder()
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .build();



    @Test
    public void testCreateUser() throws Exception {
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();

        when(usuarioService.create(usuario))
                .thenReturn(usuario);
        mvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());

    }
    @Test
    public void testNotCreateUser() throws Exception {
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();
        when(usuarioService.create(usuario))
                .thenThrow(new IllegalArgumentException("El usuario a@a.com ya existe"));

        mvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("El usuario a@a.com ya existe")));
    }

    @Test
    public void testFindAll() throws Exception {
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();
        when(usuarioService.findAll())
                .thenReturn(List.of(usuario));
        mvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindById() throws Exception {
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();
        when(usuarioService.findById("123"))
                .thenReturn(usuario);
        mvc.perform(get("/api/usuarios/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());

    }
    @Test
    public void testNotFindById() throws Exception {
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();
        when(usuarioService.findById("123"))
                .thenThrow(new EntityNotFoundException("El usuario 123 no existe"));
        mvc.perform(get("/api/usuarios/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("El usuario 123 no existe")));
    }
    @Test
    public void testUpdate() throws Exception {

        ActualizarUsuarioDto dto = ActualizarUsuarioDto.builder()
                .userId("123")
                .rol(Rol.RECEPCIONISTA)
                .build();

        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();

        when(usuarioService.update(dto.toBuilder().userId("123").build()))
                .thenReturn(usuario);
        mvc.perform(put("/api/usuarios/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindPermisos() throws Exception{
        Usuario usuario = Usuario.builder()
                .id("123")
                .email("a@a.com")
                .rol(Rol.RECEPCIONISTA)
                .build();
        when(usuarioService.findByEmail("a@a.com"))
                .thenReturn(usuario);

        mvc.perform(get("/api/usuarios/permisos").param("email", "a@a.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }
    @Test
    public void testNotFindPermisos() throws Exception{
        when(usuarioService.findByEmail("a@a.com"))
                .thenThrow(new EntityNotFoundException("El usuario a@a.com no existe"));
        mvc.perform(get("/api/usuarios/permisos").param("email", "a@a.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("El usuario a@a.com no existe")));
    }





}

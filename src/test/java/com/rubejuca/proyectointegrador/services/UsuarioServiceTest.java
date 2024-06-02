package com.rubejuca.proyectointegrador.services;


import com.rubejuca.proyectointegrador.controllers.usuarios.ActualizarUsuarioDto;
import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.model.types.Rol;
import com.rubejuca.proyectointegrador.respositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceTest {

  @Test
  public void testCreateWhenEmailNotExists() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    // Given the user
    Usuario usuario = Usuario.builder()
        .id("123")
        .rol(Rol.RECEPCIONISTA)
        .email("a@a.com")
        .medicoId(null)
        .build();

    // Simulate that the user does not exist
    Mockito.when(usuarioRepository.existsByEmail("a@a.com"))
        .thenReturn(false);

    // Simulate that the user is created
    Mockito.when(usuarioRepository.save(usuario))
        .thenReturn(usuario);

    // Create the user
    usuarioService.create(usuario);

    // Verify that the mock is called once
    Mockito.verify(usuarioRepository, Mockito.times(1))
        .existsByEmail("a@a.com");

    // Verify that the mock is called once
    Mockito.verify(usuarioRepository, Mockito.times(1))
        .save(usuario);

  }

  @Test
  public void testCreateWhenEmailExists() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    // Given the user
    Usuario usuario = Usuario.builder()
        .id("123")
        .rol(Rol.RECEPCIONISTA)
        .email("a@a.com")
        .medicoId(null)
        .build();

    // Simulate that the user exists
    Mockito.when(usuarioRepository.existsByEmail("a@a.com"))
        .thenReturn(true);

    // Create the user
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      usuarioService.create(usuario);
    });

    Assertions.assertEquals("El usuario a@a.com ya existe", exception.getMessage());

    // Verify that the mock is called once
    Mockito.verify(usuarioRepository, Mockito.times(1))
        .existsByEmail("a@a.com");

    Mockito.verifyNoMoreInteractions(usuarioRepository);
  }

  @Test
  public void testSuccessUpdate() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    ActualizarUsuarioDto dto = ActualizarUsuarioDto.builder()
        .userId("123")
        .rol(Rol.RECEPCIONISTA)
        .build();

    Usuario usuario = Usuario.builder()
        .id("123")
        .rol(Rol.RECEPCIONISTA)
        .email("a@a.com")
        .medicoId(null)
        .build();

    Mockito.when(usuarioRepository.findById("123"))
        .thenReturn(Optional.of(usuario));

    Mockito.when(usuarioRepository.save(usuario))
        .thenReturn(usuario);

    Usuario usuarioActualizado = usuarioService.update(dto);

    Assertions.assertNotNull(usuarioActualizado);

    Mockito.verify(usuarioRepository, Mockito.times(1))
        .findById("123");

    Mockito.verify(usuarioRepository, Mockito.times(1))
        .save(Mockito.any(Usuario.class));

    Mockito.verifyNoMoreInteractions(usuarioRepository);
  }
<<<<<<< HEAD
  
=======

  @Test
  public void testFindById() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);


    Usuario usuario = Usuario.builder()
        .id("123")
        .rol(Rol.RECEPCIONISTA)
        .email("a@a.com")
        .medicoId(null)
        .build();


    Mockito.when(usuarioRepository.findById("123"))
        .thenReturn(Optional.of(usuario));


    Usuario found = usuarioService.findById("123");


    Assertions.assertNotNull(found);
    Assertions.assertEquals("123", found.getId());
  }


  @Test
  public void testFindByIdwhenIdNotExists() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);


    Mockito.when(usuarioRepository.findById("123"))
        .thenReturn(Optional.empty());

    EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      usuarioService.findById("123");
    });


    Assertions.assertEquals("El usuario 123 no existe", exception.getMessage());

  }


  @Test
  public void testFindByEmailwhenEmailExists() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    Usuario usuario = Usuario.builder()
        .id("123")
        .rol(Rol.RECEPCIONISTA)
        .email("a@a.com")
        .medicoId(null)
        .build();

    Mockito.when(usuarioRepository.findByEmail("a@a.com"))
        .thenReturn(Optional.of(usuario));

    Usuario found = usuarioService.findByEmail("a@a.com");

    Assertions.assertNotNull(found);
    Assertions.assertEquals("a@a.com", found.getEmail());
  }


  @Test
  public void testFindByEmailwhenEmailNotExists() {

    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    Mockito.when(usuarioRepository.findByEmail("a@a.com"))
        .thenReturn(Optional.empty());

    EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      usuarioService.findByEmail("a@a.com");
    });

    Assertions.assertEquals("El usuario a@a.com no existe", exception.getMessage());
  }

  @Test
  public void testFindAll() {
    UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
    UsuarioService usuarioService = new UsuarioService(usuarioRepository);

    Mockito.when(usuarioRepository.findAll())
        .thenReturn(List.of(Usuario.builder()
            .id("123")
            .rol(Rol.RECEPCIONISTA)
            .email("a@a.com")
            .medicoId(null)
            .build()));

    List<Usuario> found = usuarioService.findAll();

    Assertions.assertNotNull(found);
    Assertions.assertEquals(1, found.size());
  }



>>>>>>> 49efc3d900a660a2b816a628c8618a5a2584173c
}


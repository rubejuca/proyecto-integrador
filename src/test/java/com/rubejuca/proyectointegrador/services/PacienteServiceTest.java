package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.model.types.Sexo;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import com.rubejuca.proyectointegrador.respositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacienteServiceTest {

    @Test
    public void testCreateWhenPacienteExists() {

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);


        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("123")
                .tipoDocumento(TipoDocumento.CC)
                .nombres("Rube")
                .apellidos("Juca")
                .sexo(Sexo.M)
                .email("rube@juca")
                .telefono("123")
                .fechaNacimiento(LocalDate.parse("2022-01-01"))
                .ciudad("Lima")
                .direccion("Lima")
                .build();

        Mockito.when(pacienteRepository.existsByDocumentoAndTipoDocumento("123", TipoDocumento.CC))
                .thenReturn(true);


        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pacienteService.create(paciente);
        });

        assertEquals("El paciente CC-123 ya existe", exception.getMessage());

        Mockito.verify(pacienteRepository, Mockito.times(1))
                .existsByDocumentoAndTipoDocumento("123", TipoDocumento.CC);

        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }

    @Test
    public void testCreateWhenPacienteNotExists() {

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("123")
                .tipoDocumento(TipoDocumento.CC)
                .nombres("Rube")
                .apellidos("Juca")
                .sexo(Sexo.M)
                .email("rube@juca")
                .telefono("123")
                .fechaNacimiento(LocalDate.parse("2022-01-01"))
                .ciudad("Lima")
                .direccion("Lima")
                .build();

        Mockito.when(pacienteRepository.existsByDocumentoAndTipoDocumento("123", TipoDocumento.CC))
                .thenReturn(false);


        Mockito.when(pacienteRepository.save(paciente))
                .thenReturn(paciente);

        pacienteService.create(paciente);


        Mockito.verify(pacienteRepository, Mockito.times(1))
                .existsByDocumentoAndTipoDocumento("123", TipoDocumento.CC);

        Mockito.verify(pacienteRepository, Mockito.times(1))
                .save(paciente);

        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }

    @Test
    public void testReadAll() {
        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);

        pacienteService.readAll();
        Mockito.verify(pacienteRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }

    @Test
    public void testRead() {
        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);

        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("123")
                .tipoDocumento(TipoDocumento.CC)
                .nombres("Rube")
                .apellidos("Juca")
                .sexo(Sexo.M)
                .email("rube@juca")
                .telefono("123")
                .fechaNacimiento(LocalDate.parse("2022-01-01"))
                .ciudad("Lima")
                .direccion("Lima")
                .build();

        Mockito.when(pacienteRepository.findById("1"))
                .thenReturn(java.util.Optional.of(paciente));

        pacienteService.read("1");
        Mockito.verify(pacienteRepository, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }

    @Test
    public void testSusccessUpdate() {

        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);


        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("123")
                .tipoDocumento(TipoDocumento.CC)
                .nombres("Rube")
                .apellidos("Juca")
                .sexo(Sexo.M)
                .email("rube@juca")
                .telefono("123")
                .fechaNacimiento(LocalDate.parse("2022-01-01"))
                .ciudad("Lima")
                .direccion("Lima")
                .build();

        Mockito.when(pacienteRepository.findById("1"))
                .thenReturn(java.util.Optional.of(paciente));

        Mockito.when(pacienteRepository.save(paciente))
                .thenReturn(paciente);


        pacienteService.update(paciente);
        Mockito.verify(pacienteRepository, Mockito.times(1))
                .findById("1");
        Mockito.verify(pacienteRepository, Mockito.times(1))
                .save(paciente);
        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }



    @Test
    public void testErrorUpdate() {
        PacienteRepository pacienteRepository = Mockito.mock(PacienteRepository.class);
        PacienteService pacienteService = new PacienteService(pacienteRepository);


        Paciente paciente = Paciente.builder()
                .id("1")
                .documento("123")
                .tipoDocumento(TipoDocumento.CC)
                .nombres("Rube")
                .apellidos("Juca")
                .sexo(Sexo.M)
                .email("rube@juca")
                .telefono("123")
                .fechaNacimiento(LocalDate.parse("2022-01-01"))
                .ciudad("Lima")
                .direccion("Lima")
                .build();

        Mockito.when(pacienteRepository.findById("1"))
                .thenReturn(java.util.Optional.empty());

        Mockito.when(pacienteRepository.save(paciente))
                .thenReturn(paciente);


        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            pacienteService.update(paciente);
        });
        assertEquals("El paciente con id: 1 no existe", exception.getMessage());

        Mockito.verify(pacienteRepository, Mockito.times(1)).findById("1");
        Mockito.verify(pacienteRepository, Mockito.times(0)).save(paciente);
        Mockito.verifyNoMoreInteractions(pacienteRepository);
    }

}

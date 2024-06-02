package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import com.rubejuca.proyectointegrador.respositories.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

public class MedicoServiceTest {
    @Test
    public void testCreateWhenDocumentoAndTipoDocumentoNotExists() {
        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);

        // Given the user
        Medico medico = Medico.builder()
            .id("123")
            .documento("1234")
            .tipoDocumento(TipoDocumento.TI)
            .nombres("Danna")
            .apellidos("Rojas")
            .email("danna@.com")
            .telefono("3125851410")
            .direccion("carrera 123")
            .especialidad("Cardiologo")
            .build();

        // Simulate that the user does not exist
        Mockito.when(medicoRepository.existsByDocumentoAndTipoDocumento("1234", TipoDocumento.TI))
            .thenReturn(false);
        // Simulate that the user does not exist
        Mockito.when(medicoRepository.save(medico))
            .thenReturn(medico);
        // Create the user
        medicoService.create(medico);
        // Verify that the mock is called once
        Mockito.verify(medicoRepository, Mockito.times(1))
            .existsByDocumentoAndTipoDocumento("1234", TipoDocumento.TI);
        // Verify that the mock is called once
        Mockito.verify(medicoRepository, Mockito.times(1))
            .save(medico);

    }

    @Test
    public void testCreateWhenDocumentoAndTipoDocumentoExists() {
        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);
        // Given the user
        Medico medico = Medico.builder()
                .id("123")
                .documento("1234")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .email("danna@.com")
                .telefono("3125851410")
                .direccion("carrera 123")
                .especialidad("Cardiologo")
                .build();
        // Simulate that the user does not exist
        Mockito.when(medicoRepository.existsByDocumentoAndTipoDocumento("1234", TipoDocumento.TI))
                .thenReturn(true);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            medicoService.create(medico);
        });

        Assertions.assertEquals("El médico TI-1234 ya existe", exception.getMessage());

        Mockito.verify(medicoRepository, Mockito.times(1))
                .existsByDocumentoAndTipoDocumento("1234", TipoDocumento.TI);

        Mockito.verifyNoMoreInteractions(medicoRepository);

    }

    @Test
    public void testRead(){

        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);
        // Given the user
        Medico medico = Medico.builder()
                .id("123")
                .documento("1234")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .email("danna@.com")
                .telefono("3125851410")
                .direccion("carrera 123")
                .especialidad("Cardiologo")
                .build();

        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            medicoService.read("123");
        });
        Assertions.assertEquals("El médico con id: 123 no existe", exception.getMessage());
    }

    @Test
    public void testReadAll(){

        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);
        // Given the user
        Medico medico = Medico.builder()
                .id("123")
                .documento("1234")
                .tipoDocumento(TipoDocumento.TI)
                .nombres("Danna")
                .apellidos("Rojas")
                .email("danna@.com")
                .telefono("3125851410")
                .direccion("carrera 123")
                .especialidad("Cardiologo")
                .build();

        List<Medico> medicoList = List.of(medico);
        Mockito.when(medicoRepository.findAll()).thenReturn(medicoList);
        Assertions.assertEquals(medicoList, medicoService.readAll());
    }


    @Test
    public void testSuccessUpdate() {
        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);

        // Given the user
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


        Mockito.when(medicoRepository.findById("123"))
                .thenReturn(Optional.of(medico));

       Mockito.when(medicoRepository.save(medico))
               .thenReturn(medico);

       medicoService.update(medico);

       Mockito.verify(medicoRepository, Mockito.times(1))
                       .findById("123");
       Mockito.verify(medicoRepository, Mockito.times(1))
                       .save(medico);

        Mockito.verifyNoMoreInteractions(medicoRepository);
    }

    @Test
    public void testFailUpdate() {
        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);

        // Given the user
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

        Mockito.when(medicoRepository.findById("123"))
                .thenReturn(Optional.empty());
        EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            medicoService.update(medico);
        });
        Assertions.assertEquals("El médico con id: 123 no existe", exception.getMessage());
        Mockito.verify(medicoRepository, Mockito.times(1))
                .findById("123");
        Mockito.verifyNoMoreInteractions(medicoRepository);

    }

    @Test
    public void testSearch() {
        MedicoRepository medicoRepository = Mockito.mock(MedicoRepository.class);
        MedicoService medicoService = new MedicoService(medicoRepository);
        // Given the user
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
        Mockito.when(medicoRepository.search("Cardiologo"))
                .thenReturn(List.of(medico));
        Assertions.assertEquals(List.of(medico), medicoService.search("Cardiologo"));
        Mockito.verify(medicoRepository, Mockito.times(1))
                .search("Cardiologo");
        Mockito.verifyNoMoreInteractions(medicoRepository);
    }
    }

package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.controllers.citas.ActualizarCitaDto;
import com.rubejuca.proyectointegrador.controllers.citas.CitaDto;
import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.entity.Usuario;
import com.rubejuca.proyectointegrador.model.types.EstadoCitas;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.respositories.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CitaServiceTest {


  @Test
  public void testCreateCitaConMedicoYPacienteLibres() {

    MedicoService medicoService = mock(MedicoService.class);
    PacienteService pacienteService = mock(PacienteService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository, medicoService, pacienteService);

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .build();


    when(medicoService.read("12345"))
        .thenReturn(null);

    when(citaRepository.existsByMedicoIdAndFechaHora(cita.getMedicoId(), cita.getFechaHora()))
        .thenReturn(false);

    when(pacienteService.read("1234"))
        .thenReturn(null);

    when(citaRepository.existsByPacienteIdAndFechaHora(cita.getPacienteId(), cita.getFechaHora()))
        .thenReturn(false);

    when(citaRepository.save(cita))
        .thenReturn(cita);

    // llamado
    Cita citaCreada = citaService.create(cita);

    // validaciones
    assertNotNull(citaCreada);

    verify(medicoService, times(1))
        .read("12345");

    verify(citaRepository, times(1))
        .existsByMedicoIdAndFechaHora(cita.getMedicoId(), cita.getFechaHora());

    verify(pacienteService, times(1))
        .read("1234");

    verify(citaRepository, times(1))
        .existsByPacienteIdAndFechaHora(cita.getPacienteId(), cita.getFechaHora());

    verify(citaRepository, times(1))
        .save(cita);

    verifyNoMoreInteractions(citaRepository);
    verifyNoMoreInteractions(medicoService);
    verifyNoMoreInteractions(pacienteService);
  }


  @Test
  public void testValidarMedico(){

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .build();


    when(citaRepository.existsByMedicoIdAndFechaHora(cita.getMedicoId(),cita.getFechaHora()))
        .thenReturn(true);

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      citaService.create(cita);
    });

    assertEquals("El médico ya tiene una cita asignada para la misma fecha y hora", exception.getMessage());

    verify(citaRepository, times(1))
        .existsByMedicoIdAndFechaHora(cita.getMedicoId(),cita.getFechaHora());

    verifyNoMoreInteractions(citaRepository);
    verifyNoMoreInteractions(pacienteService);
  }

  @Test
  public void testValidarPaciente(){

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .build();


    when(citaRepository.existsByPacienteIdAndFechaHora(cita.getPacienteId(),cita.getFechaHora()))
        .thenReturn(true);

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      citaService.create(cita);
    });

    assertEquals("El paciente ya tiene una cita asignada para la misma fecha y hora", exception.getMessage());

    verify(citaRepository, times(1))
        .existsByPacienteIdAndFechaHora(cita.getPacienteId(),cita.getFechaHora());
  }

  @Test
  public void testReadAll(){

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);


    Mockito.when(citaRepository.findCitas())
        .thenReturn(List.of(crearCitaInfo()));

    List<CitaInfo> citas = citaService.readAll();

    assertEquals(1, citas.size());
    Assertions.assertNotNull(citas);
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
  public void testFindById() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    when(citaRepository.findCitaById("123"))
        .thenReturn(Optional.of(crearCitaInfo()));


    CitaInfo citaInfo = citaService.findById("123");

    assertEquals("123",citaInfo.getId());
    Assertions.assertNotNull(citaInfo);
  }

  @Test
  public void testFindByIdwhenIdNotExists() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    when(citaRepository.findCitaById("123"))
        .thenReturn(Optional.empty());

    EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      citaService.findById("123");
    });

    assertEquals("La cita 123 no existe", exception.getMessage());

  }

  @Test
  public void testAtenderCita() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    Cita cita = Cita.builder()
        .id("123")
        .pacienteId("1234")
        .medicoId("12345")
        .fechaCreacion(LocalDateTime.now())
        .estado(EstadoCitas.PROGRAMADA)
        .fechaHora(LocalDateTime.of(2050, 3, 30, 14, 30))
        .diagnostico(null)
        .build();

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);


    when(citaRepository.findById("123"))
        .thenReturn(Optional.of(cita));

    Mockito.when(citaRepository.save(cita))
        .thenReturn(cita);

    Cita citaAtender= citaService.atender("123", "diagnostico");

    assertEquals(EstadoCitas.ATENDIDA,citaAtender.getEstado());
    assertEquals("diagnostico",citaAtender.getDiagnostico());
    Assertions.assertNotNull(citaAtender.getFechaAtencion());

    verify(citaRepository, times(1))
        .findById("123");

    verify(citaRepository, times(1))
        .save(cita);
  }

  @Test
  public void testAtenderCitaWhenCitaNotExists() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    when(citaRepository.findById("123"))
        .thenReturn(Optional.empty());

    EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      citaService.findById("123");
    });

    assertEquals("La cita 123 no existe", exception.getMessage());
  }

  @Test
  public void testSuccessUpdate() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

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

    when(citaRepository.findById("123"))
        .thenReturn(Optional.of(cita));

    when(citaRepository.save(cita))
        .thenReturn(cita);

    Cita citaActualizada = citaService.update("123",dto);

    assertNotNull(citaActualizada);

    verify(citaRepository, times(1))
        .findById("123");

    verify(citaRepository, times(1))
        .save(cita);


  }


  @Test
  public void testUpdateWhenCitaNotExists() {

    PacienteService pacienteService = mock(PacienteService.class);
    MedicoService medicoService = mock(MedicoService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);

    CitaService citaService = new CitaService(citaRepository,medicoService,pacienteService);

    ActualizarCitaDto dto = ActualizarCitaDto.builder()
        .fechaHora(LocalDateTime.of(2050, 3, 31, 14, 30))
        .medicoId("123456")
        .motivo("motivo actualizado")
        .build();

    when(citaRepository.findById("123"))
        .thenReturn(Optional.empty());

    EntityNotFoundException exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
      citaService.update("123",dto);
    });

    Assertions.assertEquals("La cita con id: 123 no existe", exception.getMessage());
  }

  @Test
  public void testValidarCitaFechaHoraYDtoFechaHora() {

    PacienteService pacienteService = mock(PacienteService.class);
    CitaRepository citaRepository = mock(CitaRepository.class);
    CitaService citaService = new CitaService(citaRepository , null, pacienteService);


    CitaDto dto = CitaDto.builder()
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

    when(citaRepository.findById("123"))
        .thenReturn(Optional.of(cita));

    Assertions.assertNotEquals(Cita.validateFechaHora(dto.fechaHora()),cita.getFechaHora());








  }





}

package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.controllers.citas.ActualizarCitaDto;
import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.respositories.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

  private final CitaRepository citaRepository;
  private final MedicoService medicoService;
  private final PacienteService pacienteService;

  public CitaService(CitaRepository citaRepository, MedicoService medicoService, PacienteService pacienteService) {
    this.citaRepository = citaRepository;
    this.medicoService = medicoService;
    this.pacienteService = pacienteService;
  }

  public Cita create(Cita cita) {
    validarMedico(cita.getMedicoId(), cita.getFechaHora());
    validarPaciente(cita.getPacienteId(), cita.getFechaHora());
    return citaRepository.save(cita);
  }

  private void validarMedico(String medicoId, LocalDateTime fechaHora) {
    medicoService.read(medicoId);
    if (citaRepository.existsByMedicoIdAndFechaHora(medicoId, fechaHora)) {
      throw new IllegalArgumentException(
          String.format("El médico ya tiene una cita asignada para la misma fecha y hora"));
    }
  }

  private void validarPaciente(String pacienteId, LocalDateTime fechaHora) {
    pacienteService.read(pacienteId);
    if (citaRepository.existsByPacienteIdAndFechaHora(pacienteId, fechaHora)) {
      throw new IllegalArgumentException(
          String.format("El paciente ya tiene una cita asignada para la misma fecha y hora"));
    }
  }

  public List<CitaInfo> readAll() {
    return citaRepository.findCitas();
  }

  public CitaInfo findById(String citaId) {
    return citaRepository.findCitaById(citaId)
        .orElseThrow(() -> new EntityNotFoundException("La cita %s no existe".formatted(citaId)));
  }

  public Cita update(String citaId, ActualizarCitaDto dto) {
    Cita cita = citaRepository.findById(citaId)
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("La cita con id: %s no existe", citaId)));

    if (!cita.getFechaHora().equals(dto.fechaHora())) {
      Cita.validateFechaHora(dto.fechaHora());
      validarPaciente(cita.getPacienteId(), dto.fechaHora());
    }

    if (!cita.getMedicoId().equals(dto.medicoId()) || !cita.getFechaHora().equals(dto.fechaHora())) {
      validarMedico(dto.medicoId(), dto.fechaHora());
    }

    cita.actualizar(dto.fechaHora(), dto.medicoId(), dto.motivo());

    return citaRepository.save(cita);
  }

  public Cita atender(String citaId, String diagnostico) {
    Cita cita = citaRepository.findById(citaId)
        .orElseThrow(() -> new EntityNotFoundException("La cita %s no existe".formatted(citaId)));
    cita.atender(diagnostico);
    return citaRepository.save(cita);
  }

}
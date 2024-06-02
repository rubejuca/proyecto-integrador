package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.respositories.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

  @Autowired
  PacienteRepository pacienteRepository;

  public PacienteService(PacienteRepository pacienteRepository) {
    this.pacienteRepository = pacienteRepository;
  }

  public Paciente create(Paciente paciente) {
    if (pacienteRepository.existsByDocumentoAndTipoDocumento(paciente.getDocumento(), paciente.getTipoDocumento())) {
      throw new IllegalArgumentException(
          String.format("El paciente %s-%s ya existe", paciente.getTipoDocumento(), paciente.getDocumento()));
    }
    return pacienteRepository.save(paciente);
  }

  public List<Paciente> readAll() {
    return pacienteRepository.findAll();
  }

  public Paciente read(String id) {
    return pacienteRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("El paciente con id: %s no existe", id)));
  }

  public Paciente update(Paciente paciente) {
    pacienteRepository.findById(paciente.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("El paciente con id: %s no existe", paciente.getId())));
    return pacienteRepository.save(paciente);
  }
}

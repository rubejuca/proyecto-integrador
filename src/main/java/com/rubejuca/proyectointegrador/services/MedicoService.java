package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.respositories.MedicoRepository;
import com.rubejuca.proyectointegrador.respositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

  @Autowired
  MedicoRepository medicoRepository;


  public MedicoService(MedicoRepository medicoRepository) {
    this.medicoRepository = medicoRepository;
  }


  public Medico create(Medico medico) {
    if (medicoRepository.existsByDocumentoAndTipoDocumento(medico.getDocumento(), medico.getTipoDocumento())) {
      throw new IllegalArgumentException(
          String.format("El médico %s-%s ya existe", medico.getTipoDocumento(), medico.getDocumento()));
    }
    return medicoRepository.save(medico);
  }

  public Medico read(String id) {
    return medicoRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(String.format("El médico con id: %s no existe", id)));
  }

  public List<Medico> readAll() {
    return medicoRepository.findAll();
  }

  public Medico update(Medico medico) {
    medicoRepository.findById(medico.getId())
        .orElseThrow(
            () -> new EntityNotFoundException(String.format("El médico con id: %s no existe", medico.getId())));
    return medicoRepository.save(medico);
  }

  public List<Medico> search(String value) {
    return medicoRepository.search(value);
  }
}

package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.respositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepository;

    public Cita create(Cita cita) {
        return citaRepository.save(cita);
    }

    public List<Cita> readAll() {
        return citaRepository.findAll();
    }

}
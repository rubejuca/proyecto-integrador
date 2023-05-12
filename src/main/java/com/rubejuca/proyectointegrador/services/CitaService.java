package com.rubejuca.proyectointegrador.services;

import com.rubejuca.proyectointegrador.model.entity.Cita;
import com.rubejuca.proyectointegrador.respositories.CitaInfo;
import com.rubejuca.proyectointegrador.respositories.CitaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    CitaRepository citaRepository;

    @Autowired
    MedicoService medicoService;

    @Autowired
    PacienteService pacienteService;

    public Cita create(Cita cita) {
        medicoService.read(cita.getMedicoId());
        if (citaRepository.existsByMedicoIdAndFechaHora(cita.getMedicoId(), cita.getFechaHora())) {
            throw new IllegalArgumentException(
                    String.format("El médico ya tiene una cita asignada para la misma fecha y hora"));
        }

        pacienteService.read(cita.getPacienteId());
        if (citaRepository.existsByPacienteIdAndFechaHora(cita.getPacienteId(), cita.getFechaHora())) {
            throw new IllegalArgumentException(
                    String.format("El paciente ya tiene una cita asignada para la misma fecha y hora"));
        }

        return citaRepository.save(cita);
    }

    public List<CitaInfo> readAll() {
        return citaRepository.findCitas();
    }

}
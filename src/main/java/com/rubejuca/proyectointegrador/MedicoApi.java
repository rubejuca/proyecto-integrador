package com.rubejuca.proyectointegrador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicoApi {

    private List<Medico> tablaMedicos = new ArrayList<>();

    @PostMapping("/medicos")
    public Medico crear(@RequestBody Medico medico) {
        tablaMedicos.add(medico);
        return medico;
    }

    @GetMapping("/medicos")
    public List<Medico> leer() {
        return tablaMedicos;
    }

}

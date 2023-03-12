package com.rubejuca.proyectointegrador;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EspecialidadApi {

    @GetMapping("/especialidades")
    public List<String> leer() {
        return List.of("Medicina general", "sicologia");
    }



}

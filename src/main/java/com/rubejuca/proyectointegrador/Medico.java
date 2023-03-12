package com.rubejuca.proyectointegrador;

import lombok.Data;

import java.util.List;

@Data
public class Medico {
    
    private String id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private List<String> especialidades;

}

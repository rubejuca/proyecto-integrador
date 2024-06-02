package com.rubejuca.proyectointegrador.respositories;

import com.rubejuca.proyectointegrador.model.entity.Paciente;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository  extends JpaRepository<Paciente, String> {

  boolean existsByDocumentoAndTipoDocumento(String documento, TipoDocumento tipoDocumento);


}

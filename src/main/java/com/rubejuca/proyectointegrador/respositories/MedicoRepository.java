package com.rubejuca.proyectointegrador.respositories;

import com.rubejuca.proyectointegrador.model.entity.Medico;
import com.rubejuca.proyectointegrador.model.types.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, String> {

  boolean existsByDocumentoAndTipoDocumento(String documento, TipoDocumento tipoDocumento);

  @Query("""
  SELECT m FROM Medico m 
  WHERE lower(concat(nombres, ' ', apellidos)) LIKE concat('%', lower(:value), '%')
  """)
  List<Medico> search(@Param("value") String value);

}

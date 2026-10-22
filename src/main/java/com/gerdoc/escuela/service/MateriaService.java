package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Materia;

import java.util.List;
import java.util.Optional;

public interface MateriaService
{
    // Consultar todos los alumnos
    List<Materia> findAll();
    // Consultar un alumno por ID
    Optional<Materia> findById(Long id);
    // Guardar o actualizar un alumno
    Materia save(Materia alumno);
    // Eliminar un alumno por ID
    void deleteById(Long id);
    // Verificar si existe un alumno
    boolean existsById(Long id);
}

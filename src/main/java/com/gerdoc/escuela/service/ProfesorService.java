package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorService
{
    // Consultar todos los alumnos
    List<Profesor> findAll();
    // Consultar un alumno por ID
    Optional<Profesor> findById(Long id);
    // Guardar o actualizar un alumno
    Profesor save(Profesor profesor);
    // Eliminar un alumno por ID
    void deleteById(Long id);
    // Verificar si existe un alumno
    boolean existsById(Long id);
}

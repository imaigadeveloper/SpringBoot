package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.AlumnoMateria;

import java.util.List;
import java.util.Optional;

public interface AlumnoMateriaService
{
    // Consultar todos los alumnos
    List<AlumnoMateria> findAll();
    // Consultar un alumno por ID
    Optional<AlumnoMateria> findById(Long id);
    // Guardar o actualizar un alumno
    AlumnoMateria save(AlumnoMateria alumnoMateria);
    // Eliminar un alumno por ID
    void deleteById(Long id);
    // Verificar si existe un alumno
    boolean existsById(Long id);
}

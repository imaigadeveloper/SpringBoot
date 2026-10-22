package com.gerdoc.escuela.service;


import com.gerdoc.escuela.model.ProfesorMateria;

import java.util.List;
import java.util.Optional;

public interface ProfesorMateriaService
{
    // Consultar todos los profesorMaterias
    List<ProfesorMateria> findAll();
    // Consultar un profesorMateria por ID
    Optional<ProfesorMateria> findById(Long id);
    // Guardar o actualizar un profesorMateria
    ProfesorMateria save(ProfesorMateria profesorMateria);
    // Eliminar un profesorMateria por ID
    void deleteById(Long id);
    // Verificar si existe un profesorMateria
    boolean existsById(Long id);
}

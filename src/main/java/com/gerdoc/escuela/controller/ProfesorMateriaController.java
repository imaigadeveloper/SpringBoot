package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.ProfesorMateria;
import com.gerdoc.escuela.service.ProfesorMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProfesorMateria", description = "Operaciones CRUD para la asignación de profesores a materias")
@RestController
@RequestMapping("/api/profesor-materia")
@RequiredArgsConstructor
public class ProfesorMateriaController
{
    private final ProfesorMateriaService profesorMateriaService;

    @Operation(summary = "Consultar todas las relaciones profesor-materia", description = "Obtiene la lista completa de asignaciones de profesores y materias")
    @GetMapping
    public ResponseEntity<List<ProfesorMateria>> findAll()
    {
        List<ProfesorMateria> profesorMaterias = profesorMateriaService.findAll();
        return ResponseEntity.ok(profesorMaterias);
    }

    @Operation(summary = "Consultar una relación profesor-materia por ID", description = "Obtiene una asignación por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorMateria> findById(@PathVariable Long id)
    {
        return profesorMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva relación profesor-materia", description = "Asigna un profesor a una materia en el sistema")
    @PostMapping
    public ResponseEntity<ProfesorMateria> create(@RequestBody ProfesorMateria profesorMateria)
    {
        profesorMateria.setId(null);
        ProfesorMateria nuevaRelacion = profesorMateriaService.save(profesorMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaRelacion);
    }

    @Operation(summary = "Actualizar una relación profesor-materia existente", description = "Actualiza los datos de una asignación existente en el sistema")
    @PutMapping("/{id}")
    public ResponseEntity<ProfesorMateria> update(@PathVariable Long id, @RequestBody ProfesorMateria profesorMateria)
    {
        return profesorMateriaService.findById(id)
                .map(existente -> {
                    existente.setProfesor(profesorMateria.getProfesor());
                    existente.setMateria(profesorMateria.getMateria());
                    ProfesorMateria actualizado = profesorMateriaService.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una relación profesor-materia por ID", description = "Elimina una asignación del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!profesorMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        profesorMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
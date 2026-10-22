package com.gerdoc.escuela.controller;

import com.gerdoc.escuela.model.AlumnoMateria;
import com.gerdoc.escuela.service.AlumnoMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "AlumnoMateria", description = "Operaciones CRUD para la asignación e historial de materias por alumno")
@RestController
@RequestMapping("/api/alumno-materia")
@RequiredArgsConstructor
public class AlumnoMateriaController
{
    private final AlumnoMateriaService alumnoMateriaService;

    @Operation(summary = "Consultar todas las relaciones alumno-materia", description = "Obtiene la lista completa de asignaciones de alumnos y materias")
    @GetMapping
    public ResponseEntity<List<AlumnoMateria>> findAll()
    {
        List<AlumnoMateria> alumnoMaterias = alumnoMateriaService.findAll();
        return ResponseEntity.ok(alumnoMaterias);
    }

    @Operation(summary = "Consultar una relación alumno-materia por ID", description = "Obtiene una asignación por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoMateria> findById(@PathVariable Long id)
    {
        return alumnoMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva relación alumno-materia", description = "Asigna una materia a un alumno en el sistema")
    @PostMapping
    public ResponseEntity<AlumnoMateria> create(@RequestBody AlumnoMateria alumnoMateria)
    {
        alumnoMateria.setId(null);
        AlumnoMateria nuevaRelacion = alumnoMateriaService.save(alumnoMateria);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaRelacion);
    }

    @Operation(summary = "Actualizar una relación alumno-materia existente", description = "Actualiza los datos y calificación de una asignación existente")
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoMateria> update(@PathVariable Long id, @RequestBody AlumnoMateria alumnoMateria)
    {
        return alumnoMateriaService.findById(id)
                .map(existente -> {
                    existente.setAlumno(alumnoMateria.getAlumno());
                    existente.setMateria(alumnoMateria.getMateria());
                    existente.setCalificacion(alumnoMateria.getCalificacion());
                    AlumnoMateria actualizado = alumnoMateriaService.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar una relación alumno-materia por ID", description = "Elimina una asignación del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        if (!alumnoMateriaService.existsById(id))
        {
            return ResponseEntity.notFound().build();
        }
        alumnoMateriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
package com.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.dto.RecetaDTO;
import com.demo.services.RecetaService;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;


    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")

    public ResponseEntity<List<RecetaDTO>> getTodasLasRecetas() {
        // Llamar al servicio para obtener todas las recetas
        List<RecetaDTO> recetas = recetaService.obtenerTodasLasRecetas();
        System.out.println("Total de recetas encontradas: " + recetas.size());
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/ingredientes")
    @CrossOrigin(origins = "http://localhost:3000")


    public ResponseEntity<List<RecetaDTO>> getRecetasPorIngredientes(
            @RequestParam(value = "ingredientes", required = true) String ingredientes) {
        List<String> ingredientesList = Arrays.asList(ingredientes.split(","));
        System.out.println("Ingredientes recibidos: " + ingredientesList);

        List<RecetaDTO> recetas = recetaService.findRecetasByIngredientes(ingredientesList);
        System.out.println("Recetas encontradas: " + recetas.size());

        // Simplemente devolver la lista, incluso si está vacía
        return ResponseEntity.ok(recetas);
    }
    @GetMapping("/dificultad")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<RecetaDTO>> getRecetasPorDificultad(
            @RequestParam(value = "dificultad", required = true) String dificultad) {
        System.out.println("Dificultad recibida: " + dificultad);

        List<RecetaDTO> recetas = recetaService.findRecetasByDificultad(dificultad);
        System.out.println("Recetas encontradas: " + recetas.size());
        return ResponseEntity.ok(recetas);
    }

    @PostMapping
    public ResponseEntity<RecetaDTO> crearReceta(@RequestBody RecetaDTO recetaDTO) {
        RecetaDTO nuevaReceta = recetaService.guardarReceta(recetaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReceta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDTO> actualizarReceta(@PathVariable Long id, @RequestBody RecetaDTO recetaDTO) {
        if (!recetaService.obtenerRecetaPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        recetaDTO.setId(id);
        RecetaDTO recetaActualizada = recetaService.guardarReceta(recetaDTO);
        return ResponseEntity.ok(recetaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        if (!recetaService.obtenerRecetaPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }
}

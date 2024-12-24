package com.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.RecetaDTO;
import com.demo.entity.Receta;
import com.demo.exceptions.CustomException;
import com.demo.repository.RecetaRepository;
import com.demo.utils.RecetaMapper;

@Service
public class RecetaService {

    private static final Logger logger = LoggerFactory.getLogger(RecetaService.class);

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private RecetaMapper recetaMapper;
    public List<RecetaDTO> findRecetasByDificultad(String dificultad) {
        try {
            List<Receta> recetas = recetaRepository.findByDificultad(dificultad);
            return recetas.stream()
                    .map(recetaMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al obtener recetas por dificultad: {}", e.getMessage());
            throw new CustomException("Error al obtener recetas por dificultad: " + e.getMessage(), e);
        }
    }

    public List<RecetaDTO> obtenerTodasLasRecetas() {
        try {
            List<Receta> recetas = recetaRepository.findAll();
            return recetas.stream()
                    .map(recetaMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al obtener recetas: {}", e.getMessage());
            throw new CustomException("Error al obtener recetas: " + e.getMessage(), e);
        }
    }

    public List<RecetaDTO> findRecetasByIngredientes(List <String> ingredientes) {
        try {

            List<Receta> recetas = recetaRepository.findByIngredientesIn(ingredientes);
            return recetas.stream()
                    .map(recetaMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al obtener recetas por ingredientes: {}", e.getMessage());
            throw new CustomException("Error al obtener recetas por ingredientes: " + e.getMessage(), e);
        }
    }

    public Optional<RecetaDTO> obtenerRecetaPorId(Long id) {
        Optional<Receta> receta = recetaRepository.findById(id);
        if (receta.isEmpty()) {
            throw new CustomException("Receta no encontrada con el ID: " + id);
        }
        return receta.map(recetaMapper::toDTO);
    }

    public RecetaDTO guardarReceta(RecetaDTO recetaDTO) {
        try {
            Receta receta = recetaMapper.toEntity(recetaDTO);
            Receta recetaGuardada = recetaRepository.save(receta);
            return recetaMapper.toDTO(recetaGuardada);
        } catch (Exception e) {
            logger.error("Error al guardar la receta: {}", e.getMessage());
            throw new CustomException("Error al guardar la receta: " + e.getMessage(), e);
        }
    }



    public void eliminarReceta(Long id) {
        if (recetaRepository.findById(id).isEmpty()) {
            throw new CustomException("Receta no encontrada con el ID: " + id);
        }
        recetaRepository.deleteById(id);
    }
}

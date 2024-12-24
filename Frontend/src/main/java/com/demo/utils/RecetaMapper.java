package com.demo.utils;

import com.demo.dto.RecetaDTO;
import com.demo.entity.Receta;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapper {

    public RecetaDTO toDTO(Receta receta) {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setId(receta.getId());
        recetaDTO.setNombre(receta.getNombre());
        recetaDTO.setDescripcion(receta.getDescripcion());
        recetaDTO.setImagenUrl(receta.getImagenUrl());
        recetaDTO.setTiempoCoccion(receta.getTiempoCoccion());
        recetaDTO.setDificultad(receta.getDificultad());
        // Añadir mapeo de otros campos si es necesario
        return recetaDTO;
    }

    public Receta toEntity(RecetaDTO recetaDTO) {
        Receta receta = new Receta();
        receta.setId(recetaDTO.getId());
        receta.setNombre(recetaDTO.getNombre());
        receta.setDescripcion(recetaDTO.getDescripcion());
        receta.setImagenUrl(recetaDTO.getImagenUrl());
        receta.setTiempoCoccion(recetaDTO.getTiempoCoccion());
        receta.setDificultad(recetaDTO.getDificultad());
        // Añadir mapeo de otros campos si es necesario
        return receta;
    }
}

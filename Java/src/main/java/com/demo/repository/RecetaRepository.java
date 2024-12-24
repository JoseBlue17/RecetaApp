package com.demo.repository;



import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    @Query("SELECT DISTINCT r FROM Receta r " +
           "JOIN r.recetaIngredientes ri " +
           "JOIN ri.ingrediente i " +
           "WHERE LOWER(i.nombre) IN :ingredientes")
    List<Receta> findByIngredientesIn(@Param("ingredientes") List<String> ingredientes);
    List<Receta> findByDificultad(String dificultad);

    // List<Receta> findByNombre(@Param("nombre") String nombre) ;

}


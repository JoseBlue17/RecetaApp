package com.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagenUrl;
    private int tiempoCoccion;  // Tiempo en minutos
    private String dificultad;   // Fácil, Media, Difícil

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<RecetaIngrediente> recetaIngredientes = new ArrayList<>();

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public int getTiempoCoccion() { return tiempoCoccion; }
    public void setTiempoCoccion(int tiempoCoccion) { this.tiempoCoccion = tiempoCoccion; }

    public String getDificultad() { return dificultad; }
    public void setDificultad(String dificultad) { this.dificultad = dificultad; }

    public List<RecetaIngrediente> getRecetaIngredientes() { return recetaIngredientes; }
    public void setRecetaIngredientes(List<RecetaIngrediente> recetaIngredientes) { this.recetaIngredientes = recetaIngredientes; }
}

package com.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "ingrediente")
    private List<RecetaIngrediente> recetas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<RecetaIngrediente> getRecetas() { return recetas; }
    public void setRecetas(List<RecetaIngrediente> recetas) { this.recetas = recetas; }
}

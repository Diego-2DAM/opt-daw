package com.actividadReto2BibliotecaMunicipal.reto2BibliotecaMunicipal.clases;

public class Libro {

    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int ejemplaresDisponibles;

    public Libro(int id, String titulo, String autor,
                 String categoria, int ejemplaresDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }
}

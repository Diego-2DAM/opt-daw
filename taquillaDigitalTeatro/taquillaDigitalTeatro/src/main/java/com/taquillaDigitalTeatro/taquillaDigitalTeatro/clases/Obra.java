package com.taquillaDigitalTeatro.taquillaDigitalTeatro.clases;

public class Obra {

    private int id;
    private String titulo;
    private double precioBase;
    private int asientosDisponibles;

    public Obra() {
    }

    public Obra(int id, String titulo, double precioBase, int asientosDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.precioBase = precioBase;
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
}

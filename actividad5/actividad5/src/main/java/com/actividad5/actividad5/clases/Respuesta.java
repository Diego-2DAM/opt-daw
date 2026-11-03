package com.actividad5.actividad5.clases;

public class Respuesta {

    private String cliente;
    private int totalProductos;
    private double precioTotal;
    private String mensaje;

    public Respuesta(String cliente, int totalProductos, double precioTotal, String mensaje) {
        this.cliente = cliente;
        this.totalProductos = totalProductos;
        this.precioTotal = precioTotal;
        this.mensaje = mensaje;
    }

    public String getCliente() {
        return cliente;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public String getMensaje() {
        return mensaje;
    }
}

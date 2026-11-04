package com.taquillaDigitalTeatro.taquillaDigitalTeatro.clases;

public class Reserva {

    private int eventoId;
    private String nombreCliente;
    private String tipoEntrada;
    private int cantidad;
    private String codigoCupon;

    public Reserva() {
    }

    public Reserva(int eventoId, String nombreCliente, String tipoEntrada, int cantidad, String codigoCupon) {
        this.eventoId = eventoId;
        this.nombreCliente = nombreCliente;
        this.tipoEntrada = tipoEntrada;
        this.cantidad = cantidad;
        this.codigoCupon = codigoCupon;
    }

    public int getEventoId() {
        return eventoId;
    }

    public void setEventoId(int eventoId) {
        this.eventoId = eventoId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoEntrada() {
        return tipoEntrada;
    }

    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigoCupon() {
        return codigoCupon;
    }

    public void setCodigoCupon(String codigoCupon) {
        this.codigoCupon = codigoCupon;
    }
}

package com.actividadReto2BibliotecaMunicipal.reto2BibliotecaMunicipal.clases;

public class Prestamo {

    private int libroId;
    private String nombreSocio;
    private int diasPrestamo;
    private boolean carnetPremium;

    public Prestamo() {
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public int getDiasPrestamo() {
        return diasPrestamo;
    }

    public void setDiasPrestamo(int diasPrestamo) {
        this.diasPrestamo = diasPrestamo;
    }

    public boolean isCarnetPremium() {
        return carnetPremium;
    }

    public void setCarnetPremium(boolean carnetPremium) {
        this.carnetPremium = carnetPremium;
    }
}

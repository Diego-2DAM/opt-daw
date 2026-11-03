package com.actividad5.actividad5.clases;

import java.util.List;

public class Pedido {

    private Cliente cliente;
    private List<Producto> productos;

    public Pedido() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}

package com.actividad5.actividad5.modeloControllers;

import com.actividad5.actividad5.clases.Cliente;
import com.actividad5.actividad5.clases.Producto;
import com.actividad5.actividad5.clases.Pedido;
import com.actividad5.actividad5.clases.Respuesta;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PedidoController {

    @PostMapping("/pedido")
    public Respuesta hacerPedido(@RequestBody Pedido pedido) {

        double precioTotal = 0;
        int totalProductos = 0;

        for (Producto producto : pedido.getProductos()) {

            precioTotal = precioTotal +
                    producto.getPrecioUnitario() * producto.getCantidad();

            totalProductos = totalProductos + producto.getCantidad();
        }

        String nombreCliente = pedido.getCliente().getNombre();

        Respuesta respuesta = new Respuesta(
                nombreCliente,
                totalProductos,
                precioTotal,
                "Pedido recibido correctamente"
        );

        return respuesta;
    }
}

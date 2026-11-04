package com.taquillaDigitalTeatro.taquillaDigitalTeatro.modeloControllers;


import com.taquillaDigitalTeatro.taquillaDigitalTeatro.clases.Obra;
import com.taquillaDigitalTeatro.taquillaDigitalTeatro.clases.Reserva;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaquillaControllers {

    private List<Obra> listaObras = new ArrayList<>();

    public TaquillaControllers() {
        listaObras.add(new Obra(101, "La Casa de Bernarda Alba", 25.0, 10));
        listaObras.add(new Obra(102, "Yerma", 32.0, 5));
        listaObras.add(new Obra(103, "El Tragaluz", 18.0, 20));

    }

    @PostMapping("/reservar")
    public String realizarReserva(@ModelAttribute Reserva reserva) {

        Obra obraEncontrada = null;

        for(Obra obra : listaObras) {
            if(obra.getId() == reserva.getEventoId()) {
                obraEncontrada = obra;
                break;
            }
        }

        if(obraEncontrada == null) {
            return "Obra inexistente";
        }

        if (reserva.getCantidad() <= 0) {
            return "La cantidad debe ser mayor que cero";
        }

        if(reserva.getCantidad() > obraEncontrada.getAsientosDisponibles()) {
            return "Lo siento no quedan plazas disponibles";
        } else {

            // Cogemos el precio base de la obra
            double precioUnitario = obraEncontrada.getPrecioBase();

            // Si la entrada es VIP, aumentamos el precio un 20 %
            if(reserva.getTipoEntrada().equalsIgnoreCase("VIP")) {
                precioUnitario = precioUnitario * 1.20;
            }

            // Calculamos el precio de todas las entradas
            double subtotal = precioUnitario * reserva.getCantidad();

            double total = subtotal;

            // Si compra 4 o más entradas, descontamos un 10 %
            if(reserva.getCantidad() >= 4) {
                total = total * 0.90;
            }

            // Si utiliza el cupón correcto, descontamos otro 15 %
            if("DESCUENTO15".equalsIgnoreCase(reserva.getCodigoCupon())) {
                total = total * 0.85;
            }

            // Calculamos cuánto dinero se ha descontado
            double descuentoTotal = subtotal - total;

            // Redondeamos los resultados a dos decimales
            total = Math.round(total * 100.0) / 100.0;
            descuentoTotal = Math.round(descuentoTotal * 100.0) / 100.0;

            // Restamos los asientos que se han comprado
            obraEncontrada.setAsientosDisponibles(obraEncontrada.getAsientosDisponibles() - reserva.getCantidad());

            return "Reserva solicitada. "
                    + " Asientos disponibles: " + obraEncontrada.getAsientosDisponibles()
                    + " Precio por entrada: " + precioUnitario
                    + " Subtotal: " + subtotal
                    + " Descuento total: " + descuentoTotal
                    + " Total a pagar: " + total;

        }

    }


    @GetMapping("/cartelera")
    public List<Obra> consultarCartelera(
            @RequestParam(required = false) Double precioMax,
            @RequestParam(defaultValue = "false") boolean soloDisponibles) {

        // Creamos una lista para guardar las obras que cumplen los filtros
        List<Obra> resultado = new ArrayList<>();

        // Recorremos todas las obras que tenemos guardadas
        for (Obra obra : listaObras) {

            boolean mostrar = true;

            // Si nos indican un precio máximo, comprobamos que no lo supere
            if (precioMax != null && obra.getPrecioBase() > precioMax) {
                mostrar = false;
            }

            // Si solo queremos disponibles, comprobamos que queden asientos
            if (soloDisponibles && obra.getAsientosDisponibles() <= 0) {
                mostrar = false;
            }

            // Si cumple los filtros, añadimos la obra a la lista
            if (mostrar) {
                resultado.add(obra);
            }
        }

        // Devolvemos las obras que cumplen las condiciones
        return resultado;
    }


}

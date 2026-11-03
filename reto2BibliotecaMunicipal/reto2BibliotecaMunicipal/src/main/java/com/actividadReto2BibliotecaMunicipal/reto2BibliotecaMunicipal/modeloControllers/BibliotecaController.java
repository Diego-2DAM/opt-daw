
package com.actividadReto2BibliotecaMunicipal.reto2BibliotecaMunicipal.modeloControllers;

import com.actividadReto2BibliotecaMunicipal.reto2BibliotecaMunicipal.clases.Libro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.actividadReto2BibliotecaMunicipal.reto2BibliotecaMunicipal.clases.Prestamo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class BibliotecaController {

    // Aquí guardamos los libros de la biblioteca
    private List<Libro> libros = new ArrayList<>();

    // Creamos los cinco libros iniciales
    public BibliotecaController() {

        libros.add(new Libro(1, "Don Quijote de la Mancha",
                "Miguel de Cervantes", "novela", 3));

        libros.add(new Libro(2, "La Celestina",
                "Fernando de Rojas", "teatro", 2));

        libros.add(new Libro(3, "El Principito",
                "Antoine de Saint-Exupéry", "infantil", 4));

        libros.add(new Libro(4, "La Regenta",
                "Leopoldo Alas", "novela", 0));

        libros.add(new Libro(5, "Platero y yo",
                "Juan Ramón Jiménez", "poesia", 2));
    }

    // Mostramos todos los libros o filtramos por categoría y autor
    @GetMapping("/libros")
    public List<Libro> buscarLibros(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String autor,
            @RequestParam(defaultValue = "false") boolean soloDisponibles) {

        List<Libro> resultado = new ArrayList<>();

        for (Libro libro : libros) {

            boolean mostrar = true;

            if (categoria != null && !libro.getCategoria().equalsIgnoreCase(categoria)) {
                mostrar = false;
            }

            if (autor != null && !libro.getAutor().equalsIgnoreCase(autor)) {
                mostrar = false;
            }

            if (soloDisponibles && libro.getEjemplaresDisponibles() == 0) {
                mostrar = false;
            }

            if (mostrar) {
                resultado.add(libro);
            }
        }

        return resultado;
    }

    // Buscamos un único libro utilizando su ID
    @GetMapping("/libro")
    public Object buscarLibro(@RequestParam int id) {

        for (Libro libro : libros) {

            if (libro.getId() == id) {
                return libro;
            }
        }

        return "Libro no encontrado";
    }

    // Recogemos los datos del formulario y hacemos el préstamo
    @PostMapping("/prestamo")
    public Map<String, Object> realizarPrestamo(@ModelAttribute Prestamo prestamo) {

        // Primero buscamos el libro que ha elegido el socio
        Libro libroEncontrado = null;

        for (Libro libro : libros) {
            if (libro.getId() == prestamo.getLibroId()) {
                libroEncontrado = libro;
                break;
            }
        }

        // Si no encontramos el libro, rechazamos el préstamo
        if (libroEncontrado == null) {
            return Map.of(
                    "estado", "RECHAZADO",
                    "motivo", "Libro no encontrado"
            );
        }

        // Comprobamos si quedan ejemplares disponibles
        if (libroEncontrado.getEjemplaresDisponibles() <= 0) {
            return Map.of(
                    "estado", "RECHAZADO",
                    "motivo", "Sin ejemplares disponibles"
            );
        }

        // Comprobamos que los días estén entre 1 y 30
        if (prestamo.getDiasPrestamo() < 1 || prestamo.getDiasPrestamo() > 30) {
            return Map.of(
                    "estado", "RECHAZADO",
                    "motivo", "Duración no válida (1-30 días)"
            );
        }

        // Si todo está bien, restamos un ejemplar
        libroEncontrado.setEjemplaresDisponibles(
                libroEncontrado.getEjemplaresDisponibles() - 1
        );

        // Calculamos los días totales del préstamo
        int diasTotales = prestamo.getDiasPrestamo();

        if (prestamo.isCarnetPremium()) {
            diasTotales = diasTotales + 7;
        }

        // Calculamos la fecha de devolución
        LocalDate fechaDevolucion = LocalDate.now().plusDays(diasTotales);

        return Map.of(
                "estado", "CONCEDIDO",
                "socio", prestamo.getNombreSocio(),
                "libro", libroEncontrado.getTitulo(),
                "diasTotales", diasTotales,
                "fechaDevolucion", fechaDevolucion.toString()
        );
    }

}

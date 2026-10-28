package com.ejercicio9_1.ejercicio9_1.controllers;

import com.ejercicio9_1.ejercicio9_1.models.Producto;
import com.ejercicio9_1.ejercicio9_1.models.Respuesta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductosControllers {

    List<Producto> listaProductos = new ArrayList<>();

    public ProductosControllers() {
        listaProductos.add(new Producto(1, "Pan", 1.50));
        listaProductos.add(new Producto(2, "Leche", 1.20));
        listaProductos.add(new Producto(3, "Huevos", 2.50));
    }

    @GetMapping("/productos")
    public ResponseEntity<?> mostrarProducos() {

        if(listaProductos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Respuesta("Lista vacía"));
        }

        return ResponseEntity.ok(listaProductos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProducto(@RequestParam int id) {

        Optional<Producto> encontrado = Optional.empty();

        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                encontrado = Optional.of(producto);
                break;
            }
        }
        return encontrado.<ResponseEntity<?>>map(producto ->ResponseEntity.ok(producto)).orElse(ResponseEntity.status(404).body(new Respuesta("No existe ese producto")));
    }
}

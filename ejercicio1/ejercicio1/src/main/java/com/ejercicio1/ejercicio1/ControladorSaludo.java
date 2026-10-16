package com.ejercicio1.ejercicio1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorSaludo {

    @GetMapping("/saludo")

    public String saludo() {
        return "¡Hola, Sptring Boot!";
    }
}

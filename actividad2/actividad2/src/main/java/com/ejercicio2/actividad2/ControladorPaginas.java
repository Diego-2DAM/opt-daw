package com.ejercicio2.actividad2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Indica que esta clase responde a peticiones del navegador.
@RestController
public class ControladorPaginas {

    //Página de Inicio
    @GetMapping(value = "/inicio", produces = "text/html") // Indica que devolvemos contenido HTML.

    public String inicio() {
        return "<html" +
                    "<body>" +
                        "<h1>Bienvenido a la aplicación</h1>" +
                        "<p>Esta es mi primera aplicación con Spring Boot.</p>" +
                    "</body>" +
                "</html>";
    }

    @GetMapping(value = "/contacto", produces = "text/html") // Indica que devolvemos contenido HTML.

    public String contacto() {
        return "<html>" +
                    "<body>" +
                        "<h1> Página de contacto </h1>" +
                        "<p> Correo: ejemplo@gmail.com </p>" +
                        "<p> Teléfono: 600123456 </p>" +
                    "</body>" +
                "</html>";
    }

}

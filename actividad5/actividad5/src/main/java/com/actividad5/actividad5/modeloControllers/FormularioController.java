
package com.actividad5.actividad5.modeloControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FormularioController {

    @GetMapping("/formulario")
    public String mostrarFormulario() {
        return "formulario";
    }

    @PostMapping("/procesar")
    @ResponseBody
    public String procesarFormulario(
            @RequestParam("nombre") String nombre,
            @RequestParam("edad") int edad) {

        return "Formulario recibido. Nombre: " + nombre + ", edad: " + edad;
    }
}

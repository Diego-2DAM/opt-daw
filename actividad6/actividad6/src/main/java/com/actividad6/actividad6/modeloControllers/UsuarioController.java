
package com.actividad6.actividad6.modeloControllers;

import com.actividad6.actividad6.clases.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UsuarioController {

    // Aquí vamos guardando los usuarios y sus contraseñas
    private Map<String, String> usuarios = new HashMap<>();

    public UsuarioController() {
        usuarios.put("ana@example.com", "246810");
        usuarios.put("luis@example.com", "clave123");
    }

    // Mostramos el formulario de registro
    @GetMapping(value = "/registro", produces = "text/html")
    public String mostrarRegistro() {

        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Registro</title>
                </head>
                <body>
                    <h1>Registro de usuario</h1>

                    <form action="/registro" method="post">
                        <p>Nombre: <input type="text" name="nombre" required></p>
                        <p>Apellidos: <input type="text" name="apellidos"></p>
                        <p>Correo: <input type="email" name="correo" required></p>
                        <p>Contraseña: <input type="password" name="contrasena"></p>

                        <button type="submit">Registrarse</button>
                    </form>

                    <a href="/login">Ir al login</a>
                </body>
                </html>
                """;
    }

    // Recogemos los datos y registramos al usuario
    @PostMapping("/registro")
    public String registrar(@ModelAttribute Usuario usuario) {

        if (usuario.getNombre() == null ||
                usuario.getNombre().isBlank() ||
                usuario.getCorreo() == null ||
                usuario.getCorreo().isBlank()) {

            return "El nombre y el correo son obligatorios";
        }

        String contrasena = usuario.getContrasena();
        boolean generada = false;

        if (contrasena == null || contrasena.isBlank()) {
            Random random = new Random();
            int numero = random.nextInt(1000000);

            contrasena = String.format("%06d", numero);
            generada = true;
        }

        usuarios.put(usuario.getCorreo(), contrasena);

        String apellidos = usuario.getApellidos();

        if (apellidos == null) {
            apellidos = "";
        }

        String respuesta = "Usuario registrado: " +
                usuario.getNombre() + " " + apellidos +
                "\nCorreo: " + usuario.getCorreo();

        if (generada) {
            respuesta = respuesta + "\nContraseña generada: " + contrasena;
        }

        return respuesta;
    }

    // Mostramos el formulario para iniciar sesión
    @GetMapping(value = "/login", produces = "text/html")
    public String mostrarLogin() {

        return """
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Login</title>
                </head>
                <body>
                    <h1>Iniciar sesión</h1>

                    <form action="/login" method="post">
                        <p>Correo: <input type="email" name="usuario" required></p>
                        <p>Contraseña: <input type="password" name="contrasena" required></p>

                        <button type="submit">Entrar</button>
                    </form>

                    <a href="/registro">Registrarse</a>
                </body>
                </html>
                """;
    }

    // Comprobamos los datos del login
    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam String usuario,
            @RequestParam String contrasena) {

        if (!usuarios.containsKey(usuario)) {
            return "Usuario inexistente.";
        }

        if (!usuarios.get(usuario).equals(contrasena)) {
            return "Contraseña incorrecta.";
        }

        return "Inicio de sesión correcto.";
    }
}

package com.ejercicio4.ejercicio4;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablaController {

    private static final int VALOR_POR_DEFECTO_FILAS = 3;
    private static final int VALOR_POR_DEFECTO_COLUMNAS = 3;

    @GetMapping(value = "/tabla", produces = MediaType.TEXT_HTML_VALUE)
    public String generarTabla(
            @RequestParam(required = false) String filas,
            @RequestParam(required = false) String columnas) {

        int numeroFilas = convertirYValidar(filas, VALOR_POR_DEFECTO_FILAS);
        int numeroColumnas = convertirYValidar(columnas, VALOR_POR_DEFECTO_COLUMNAS);

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>");
        html.append("<html lang='es'>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Tabla dinámica</title>");
        html.append("<style>");
        html.append("body { font-family: Arial, sans-serif; margin: 30px; }");
        html.append("table { border-collapse: collapse; }");
        html.append("th, td { border: 1px solid black; padding: 10px; text-align: center; }");
        html.append("th { background-color: #4CAF50; color: white; }");
        html.append("</style>");
        html.append("</head>");

        html.append("<body>");
        html.append("<h1>Tabla de ")
                .append(numeroFilas)
                .append(" filas y ")
                .append(numeroColumnas)
                .append(" columnas</h1>");

        html.append("<table>");

        // Encabezado
        html.append("<thead>");
        html.append("<tr>");

        for (int columna = 1; columna <= numeroColumnas; columna++) {
            html.append("<th>Columna ")
                    .append(columna)
                    .append("</th>");
        }

        html.append("</tr>");
        html.append("</thead>");

        // Cuerpo de la tabla
        html.append("<tbody>");

        for (int fila = 1; fila <= numeroFilas; fila++) {
            html.append("<tr>");

            for (int columna = 1; columna <= numeroColumnas; columna++) {
                html.append("<td>Fila ")
                        .append(fila)
                        .append(", Columna ")
                        .append(columna)
                        .append("</td>");
            }

            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }

    private int convertirYValidar(String valor, int valorPorDefecto) {

        if (valor == null || valor.isBlank()) {
            return valorPorDefecto;
        }

        try {
            int numero = Integer.parseInt(valor);

            if (numero < 1) {
                return 1;
            }

            if (numero > 20) {
                return 20;
            }

            return numero;

        } catch (NumberFormatException e) {
            return valorPorDefecto;
        }
    }
}
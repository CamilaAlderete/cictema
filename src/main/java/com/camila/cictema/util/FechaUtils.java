package com.camila.cictema.util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaUtils {

    // Método para convertir una cadena a LocalDateTime al inicio del día (00:00)
    public static LocalDateTime convertirStringAFechaInicioDia(String fechaString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        return fecha.atStartOfDay(); // 00:00:00 del día
    }

    // Método para convertir una cadena a LocalDateTime al final del día (23:59:59)
    public static LocalDateTime convertirStringAFechaFinDia(String fechaString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        return fecha.atTime(23, 59, 59); // 23:59:59 del día
    }
}

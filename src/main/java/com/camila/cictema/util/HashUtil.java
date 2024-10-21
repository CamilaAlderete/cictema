package com.camila.cictema.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {
    public static String hashPassword(String password) {
        try {
            // Instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Convertir la contraseña en bytes y hacer el hash
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convertir los bytes a hexadecimal para hacerlo legible
            return bytesToHex(encodedHash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Método auxiliar para convertir los bytes en hexadecimal
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

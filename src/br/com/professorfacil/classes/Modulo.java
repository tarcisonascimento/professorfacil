package br.com.professorfacil.classes;

import java.security.MessageDigest;

/**
 *
 * @author Tarciso Nascimento
 */
public class Modulo {

    public static String dois;
    
    public static void main(String args[]) throws Exception {
        String senha = dois;
        System.out.println(modulo1(senha));
    }

    public static String modulo1(String senha) throws Exception {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder texto = new StringBuilder();
        for (byte b : hash) {
            texto.append(String.format("%02X", 0xFF & b));
        }
        return texto.toString();
    }

}

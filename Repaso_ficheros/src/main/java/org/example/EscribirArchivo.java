package org.example;

import java.io.*;

public class EscribirArchivo {
    public static void main(String[] args) {
        try {
            FileWriter fw = new FileWriter("archivo.txt", true);  // 'true' para añadir texto sin sobrescribir
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Este es un nuevo texto.");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

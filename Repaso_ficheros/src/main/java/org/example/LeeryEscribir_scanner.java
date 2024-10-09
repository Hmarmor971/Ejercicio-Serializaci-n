package org.example;

import java.io.*;
import java.util.Scanner;

public class LeeryEscribir_scanner {
    public static void main(String[] args) {

        File archivoCon = new File("archivo_relleno.txt");
        File archivoSin = new File("archivo_SinRelleno.txt");

        if (!archivoCon.exists()) {
            try {
                archivoCon.createNewFile();
                System.out.println("Archivo creado: " + archivoCon.getName());
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
            }
        }

        try {
            String ruta3 = archivoCon.getPath();
            FileWriter fw1 = new FileWriter(ruta3);
            BufferedWriter bw1 = new BufferedWriter(fw1);

            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce el texto Inicial:");
            String texto;

            while(sc.hasNextLine()){
                texto = sc.nextLine();
                bw1.write(texto);
                bw1.newLine();
            }

            bw1.close();

        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }


        if (!archivoSin.exists()) {
            try {
                archivoSin.createNewFile();
                System.out.println("Archivo creado: " + archivoSin.getName());
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
            }
        }

        try {
            String ruta = archivoCon.getPath();
            String ruta2 = archivoSin.getPath();

            FileReader fr = new FileReader(ruta);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter(ruta2, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            bw.close();
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

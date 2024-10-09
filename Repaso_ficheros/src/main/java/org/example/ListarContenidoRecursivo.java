package org.example;
import java.io.File;

public class ListarContenidoRecursivo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ListarContenidoRecursivo <ruta_de_la_carpeta>");
            System.exit(1);
        }
        String rutaCarpeta = args[0];
        listarContenidoRecursivo(new File(rutaCarpeta), -1);
    }

    public static void listarContenidoRecursivo(File carpeta, int cont) {

        if (carpeta.isDirectory()) {
            System.out.println("Carpeta: " + tree(cont) + carpeta.getAbsolutePath());
            File[] archivos = carpeta.listFiles();

            if (archivos != null) {
                for (File archivo : archivos) {
                    cont++;
                    listarContenidoRecursivo(archivo, cont);
                }
            }

        } else if (carpeta.isFile()) {
            System.out.println("Archivo: " + tree(cont) + carpeta.getAbsolutePath());
        }
    }

    public static StringBuilder tree(int cont) {
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < cont; i++) {
            if (i == cont-1) {
                st.append("|___");
            } else{
                st.append("\t");
            }
        }
        return st;
    }
}

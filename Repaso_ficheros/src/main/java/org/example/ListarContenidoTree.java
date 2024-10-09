package org.example;

import java.io.File;

public class ListarContenidoTree {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ListarContenidoRecursivo <ruta_de_la_carpeta>");
            System.exit(1);
        }

        String rutaCarpeta = args[0];
        listarContenidoRecursivo(new File(rutaCarpeta), "", true);
    }

    public static void listarContenidoRecursivo(File carpeta, String prefijo, boolean esUltimo) {


        if (carpeta.isDirectory()) {
            //Carpeta Usuario
            // Muestra el nombre de la carpeta con el prefijo adecuado
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + "Carpeta: " + carpeta.getName());

            //Un array de objetos File con dos elementos: la carpeta etc y la carpeta home
            File[] archivos = carpeta.listFiles();


            if (archivos != null) {
                // Recorre los archivos y carpetas dentro de la carpeta actual
                for (int i = 0; i < archivos.length; i++) {
                    // Determina si es el último archivo para ajustar el prefijo de la siguiente línea
                    boolean esUltimoHijo = (i == archivos.length - 1);
                    listarContenidoRecursivo(archivos[i], prefijo + (esUltimo ? "    " : "│   "), esUltimoHijo);
                }
            }
        }else if (carpeta.isFile()) {
            // Muestra el nombre del archivo con el prefijo adecuado
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + "Archivo: " + carpeta.getName());
        }
    }
}

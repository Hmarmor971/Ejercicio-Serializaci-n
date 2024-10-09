package org.example;


import java.io.File;
import java.io.IOException;

public class EjemploFile {
    public static void main(String[] args) {
        // Crear un objeto File
        File archivo = new File("miArchivo.txt");

        // Verificar si el archivo existe
        if (!archivo.exists()) {
            try {
                // Crear un nuevo archivo
                archivo.createNewFile();
                System.out.println("Archivo creado: " + archivo.getName());
            } catch (IOException e) {
                System.out.println("Error al crear el archivo.");
            }
        }

        // Obtener propiedades del archivo
        System.out.println("Ruta: " + archivo.getAbsolutePath());
        System.out.println("Tamaño: " + archivo.length() + " bytes");
        System.out.println("Última modificación: " + archivo.lastModified());

        // Verificar si es un archivo
        if (archivo.isFile()) {
            System.out.println(archivo.getName() + " es un archivo.");
        }

        // Renombrar el archivo
        File nuevoArchivo = new File("archivoRenombrado.txt");
        if (archivo.renameTo(nuevoArchivo)) {
            System.out.println("Archivo renombrado a: " + nuevoArchivo.getName());
        } else {
            System.out.println("No se pudo renombrar el archivo.");
        }

        // Eliminar el archivo
        if (nuevoArchivo.delete()) {
            System.out.println("Archivo eliminado.");
        } else {
            System.out.println("No se pudo eliminar el archivo.");
        }
    }
}

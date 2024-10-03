import java.io.*;
import java.util.ArrayList;

public class Biblioteca {


    private ArrayList<Libro> listaLibros;

    public Biblioteca(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public void agregarLibro(Libro a) {
        listaLibros.add(a);
    }

    public void mostrarLibros() {
        for (Libro libro : listaLibros) {
            System.out.println(libro);
        }
    }

    public void serializarLibros() { //Serializa los objetos Libro del ArrayList de Biblioteca
        try (FileOutputStream fos = new FileOutputStream("./src/libros.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Libro libro : listaLibros) {
                oos.writeObject(libro);
            }

            System.out.println("Serializacion completada.");
        } catch (IOException e) {
            System.out.println("Error al intentar serializar.");
        }
    }

    public void deserializarLibros() { //Deserializa los libros encontrados en el archivo serializado datos.dat y los muestra por pantalla.

        try (FileInputStream fis = new FileInputStream("./src/libros.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Libro libro = (Libro) ois.readObject();
                    System.out.println("\nInformación del libro:");
                    System.out.println(libro.toString());

                } catch (EOFException e) {
                    System.out.println("\nDeserializacion terminada.");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al intentar deserializar.");
        }
    }


    public ArrayList<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

}


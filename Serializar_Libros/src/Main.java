import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Libro libro1 = new Libro("Miguel y su burrito sabanero", "Michel de Cervantes", "89y9348397", 10.5, 11.2);
        Libro libro2 = new Libro("Sara rules", "Sabanero el burro", "hfj48fj94", 50.2, 0.0);
        Libro libro3 = new Libro("Antonio: las rodillas me duelen", "Antonio", "3894839d39", 0.5, 90.5);
        Libro libro4 = new Libro("Michel master class", "Evil Miguel de Cervantes", "84h9f440", 40.7, 20.7);

        ArrayList<Libro> miLista = new ArrayList<>();
        Biblioteca miBiblioteca = new Biblioteca(miLista);

        miBiblioteca.agregarLibro(libro1);
        miBiblioteca.agregarLibro(libro2);
        miBiblioteca.agregarLibro(libro3);
        miBiblioteca.agregarLibro(libro4);

        miBiblioteca.serializarLibros();

        miBiblioteca.deserializarLibros();


    }
}

import java.io.Serializable;

public class Libro implements Serializable {

    private String titulo;
    private String autor;
    private String isbn;
    private double precio;
    private transient double descuento;

    public Libro(String titulo, String autor, String isbn, double precio, double descuento) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.precio = precio;
        this.descuento = descuento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Libro: \n" +
                "titulo: " + titulo + "\n" +
                "autor: " + autor + "\n" +
                "isbn: " + isbn + "\n" +
                "precio: " + precio + "\n" +
                "descuento: " + descuento + "\n";
    }

}

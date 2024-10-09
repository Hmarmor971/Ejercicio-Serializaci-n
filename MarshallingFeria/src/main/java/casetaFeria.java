import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlType;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


@XmlRootElement(name = "casetaferia")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "nombre", "titular", "aforo", "tipoCaseta"})

public class casetaFeria {

    @XmlAttribute(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "titular")
    private String titular;

    @XmlElement(name = "aforo")
    private int aforo;

    @XmlElement(name = "tipoCaseta")
    private String tipoCaseta;

    public static List<casetaFeria> listarCasetas() {
        try (FileReader fl = new FileReader("./src/main/java/casetas.txt");
             BufferedReader br = new BufferedReader(fl)) {
            List<casetaFeria> casList = new ArrayList<>();
            String linea;
            int cont = 1;

            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(" - ");
                casetaFeria caseta = new casetaFeria(cont, cadena[0], cadena[1], parseInt(cadena[2]), cadena[3]);
                casList.add(caseta);
                cont++;
            }
            return casList;

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo de datos.");
            return null;
        }
    }

    public casetaFeria(int id, String nombre, String titular, int aforo, String tipoCaseta) {
        this.id = id;
        this.nombre = nombre;
        this.titular = titular;
        this.aforo = aforo;
        this.tipoCaseta = tipoCaseta;
    }

    // Constructor por defecto (necesario para JAXB)
    public casetaFeria() {
    }

    @Override
    public String toString() {
        return "casetaFeria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titular='" + titular + '\'' +
                ", aforo=" + aforo +
                ", tipoCaseta='" + tipoCaseta + '\'' +
                '}'+ "\n";
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getTipoCaseta() {
        return tipoCaseta;
    }

    public void setTipoCaseta(String tipoCaseta) {
        this.tipoCaseta = tipoCaseta;
    }
}

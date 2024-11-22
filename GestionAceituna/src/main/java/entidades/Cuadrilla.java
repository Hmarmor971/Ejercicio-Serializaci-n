package entidades;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "cuadrilla")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "nombre", "supervisor_id", "trabajadores", "olivares"})
public class Cuadrilla {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "supervisor_id")
    private int supervisor_id;

    @XmlElementWrapper(name = "trabajadores")
    @XmlElement(name = "trabajador")
    private List<Trabajador> trabajadores;

    @XmlElementWrapper(name = "olivares")
    @XmlElement(name = "olivar")
    private List<Olivar> olivares;

    //Constructor
    public Cuadrilla(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Cuadrilla(int id, String nombre, int supervisor_id) {
        this.id = id;
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
    }

    public Cuadrilla(int id, String nombre, int supervisor_id, List<Trabajador> trabjadores, List<Olivar> olivarres) {
        this.id = id;
        this.nombre = nombre;
        this.supervisor_id = supervisor_id;
        this.trabajadores = trabjadores;
        this.olivares = olivarres;
    }

    public Cuadrilla() {
    }

    //Getters y Setters

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getSupervisor_id() {
        return supervisor_id;
    }


    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public List<Trabajador> getTrabjadores() {
        return trabajadores;
    }

    public void setTrabjadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }


    public List<Olivar> getOlivares() {
        return olivares;
    }


    public void setOlivares(List<Olivar> olivares) {
        this.olivares = olivares;
    }

    @Override
    public String toString() {
        return "Cuadrilla{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", supervisor_id=" + supervisor_id +
                ", trabajadores=" + trabajadores +
                ", olivares=" + olivares +
                '}';
    }
}

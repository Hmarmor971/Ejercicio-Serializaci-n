package entidades;

import java.util.List;

public class Cuadrilla {

    private int id;
    private String nombre;
    private int supervisor_id;
    private List<Trabajador> trabjadores;
    private List<Olivar> olivares;

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
        this.trabjadores = trabjadores;
        this.olivares = olivarres;
    }

    //Getters y Setters
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

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
    }

    public List<Trabajador> getTrabjadores() {
        return trabjadores;
    }

    public void setTrabjadores(List<Trabajador> trabjadores) {
        this.trabjadores = trabjadores;
    }

    public List<Olivar> getOlivares() {
        return olivares;
    }

    public void setOlivares(List<Olivar> olivares) {
        this.olivares = olivares;
    }
}

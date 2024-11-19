package entidades;

import java.time.LocalDate;

public class Produccion {

    private int id;
    private int cuadrilla_id;
    private int olivar_id;
    private int almazada_id;
    private LocalDate fecha;
    private double cantidadRecolectada;

    //Constructor
    public Produccion(int cuadrilla_id, int olivar_id, int almazada_id, LocalDate fecha, double cantidadRecolectada) {
        this.cuadrilla_id = cuadrilla_id;
        this.olivar_id = olivar_id;
        this.almazada_id = almazada_id;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    public Produccion(int id, int cuadrilla_id, int olivar_id, int almazada_id, LocalDate fecha, double cantidadRecolectada) {
        this.id = id;
        this.cuadrilla_id = cuadrilla_id;
        this.olivar_id = olivar_id;
        this.almazada_id = almazada_id;
        this.fecha = fecha;
        this.cantidadRecolectada = cantidadRecolectada;
    }

    //Getter y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuadrilla_id() {
        return cuadrilla_id;
    }

    public void setCuadrilla_id(int cuadrilla_id) {
        this.cuadrilla_id = cuadrilla_id;
    }

    public int getOlivar_id() {
        return olivar_id;
    }

    public void setOlivar_id(int olivar_id) {
        this.olivar_id = olivar_id;
    }

    public int getAlmazada_id() {
        return almazada_id;
    }

    public void setAlmazada_id(int almazada_id) {
        this.almazada_id = almazada_id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCantidadRecolectada() {
        return cantidadRecolectada;
    }

    public void setCantidadRecolectada(double cantidadRecolectada) {
        this.cantidadRecolectada = cantidadRecolectada;
    }
}

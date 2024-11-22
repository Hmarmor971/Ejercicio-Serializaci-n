package entidades;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "olivar")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "ubicacion", "hectareas", "produccionAnual", "cuadrillas"})
public class Olivar {

    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "ubicacion")
    private String ubicacion;

    @XmlElement(name = "hectareas")
    private double hectareas;

    @XmlElement(name = "produccionAnual")
    private double produccionAnual;

    @XmlElementWrapper(name = "cuadrillas")
    @XmlElement(name = "cuadrillas")
    private List<Cuadrilla> cuadrillas;

    //Constructores
    public Olivar(int id, String ubicacion, double hectareas, double produccionAnual) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
    }

    public Olivar(int id, String ubicacion, double hectareas, double produccionAnual, List<Cuadrilla> cuadrillas) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.hectareas = hectareas;
        this.produccionAnual = produccionAnual;
        this.cuadrillas = cuadrillas;
    }

    public Olivar() {
    }

    //Getters y Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getHectareas() {
        return hectareas;
    }

    public void setHectareas(double hectareas) {
        this.hectareas = hectareas;
    }

    public double getProduccionAnual() {
        return produccionAnual;
    }

    public void setProduccionAnual(double produccionAnual) {
        this.produccionAnual = produccionAnual;
    }

    public List<Cuadrilla> getCuadrillas() {
        return cuadrillas;
    }

    public void setCuadrillas(List<Cuadrilla> cuadrillas) {
        this.cuadrillas = cuadrillas;
    }

    @Override
    public String toString() {
        return "Olivar{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", hectareas=" + hectareas +
                ", produccionAnual=" + produccionAnual +
                ", cuadrillas=" + cuadrillas +
                '}';
    }
}

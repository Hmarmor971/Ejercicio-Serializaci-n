package model;

import java.time.LocalDate;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "coche")
public class Coche {

    private String marca;
    private String modelo;
    private String matricula;
    private int anno;
    private  String fechaVenta;

    public Coche(String marca, String modelo, String matricula, int anno, String fechaVenta) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anno = anno;
        this.fechaVenta = fechaVenta;
    }

    public Coche() {
    }

    @XmlElement
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @XmlElement
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @XmlElement
    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    @XmlElement
    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    @Override
    public String toString() {
        return "Coche: marca= " + marca + ", modelo= " + modelo + ", matricula= " + matricula  + ", anno= " + anno + ", fechaVenta= " + fechaVenta;
    }
}

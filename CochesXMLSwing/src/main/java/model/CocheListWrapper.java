package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "coches")
public class CocheListWrapper {
    private List<Coche> coches;

    public CocheListWrapper() {
        // Constructor por defecto necesario para JAXB
    }

    public CocheListWrapper(List<Coche> coches) {
        this.coches = coches;
    }

    @XmlElement(name = "coche")
    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }
}

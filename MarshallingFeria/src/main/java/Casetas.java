import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "casetas")
public class Casetas {

    private List<casetaFeria> casetaList;

    public Casetas(List<casetaFeria> casetas) {
        this.casetaList = casetas;
    }

    public Casetas() {}

    @XmlElement(name = "casetaferia")
    public List<casetaFeria> getCasetas() {
        return casetaList;
    }

    public void setCasetas(List<casetaFeria> casetas) {
        this.casetaList = casetas;
    }

    @Override
    public String toString() {
        return "Casetas{" +
                "casetaList=" + casetaList +
                '}';
    }
}

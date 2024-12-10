package Modelos;

import org.bson.Document;

import java.util.List;
import java.util.stream.Collectors;


public class Programa {

    private String nombre;
    private String categoria;
    private Horario horario;
    private List<Audiencia> audiencia;
    private List<Colaborador> colaborador;

    public Programa(String nombre, String categoria, Horario horario, List<Audiencia> audiencia, List<Colaborador> colaborador) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.horario = horario;
        this.audiencia = audiencia;
        this.colaborador = colaborador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Audiencia> getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(List<Audiencia> audiencia) {
        this.audiencia = audiencia;
    }

    public List<Colaborador> getColaborador() {
        return colaborador;
    }

    public void setColaborador(List<Colaborador> colaborador) {
        this.colaborador = colaborador;
    }

    // Convierte un Programa a un documento de MongoDB
    public Document toDocument() {
        return new Document("nombre", nombre)
                .append("categoria", categoria)
                .append("horario", horario.toDocument()) // Convertir Horario a Document
                .append("audiencia", audiencia.stream().map(Audiencia::toDocument).collect(Collectors.toList())) // Convertir lista de Audiencia a Document
                .append("colaborador", colaborador.stream().map(Colaborador::toDocument).collect(Collectors.toList())); // Convertir lista de Colaborador a Document
    }

    // Crear un Programa a partir de un documento de MongoDB
    public static Programa fromDocument(Document document) {
        String nombre = document.getString("nombre");
        String categoria = document.getString("categoria");
        Document horarioDoc = (Document) document.get("horario");
        Horario horario = Horario.fromDocument(horarioDoc);  // Convertir el Document a Horario
        List<Audiencia> audiencia = fromDocumentListAudiencia(document.getList("audiencia", Document.class));
        List<Colaborador> colaborador = fromDocumentListColaborador(document.getList("colaborador", Document.class));
        return new Programa(nombre, categoria, horario, audiencia, colaborador);
    }

    @Override
    public String toString() {
        return "Programa{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", horario=" + horario +
                ", audiencia=" + audiencia +
                ", colaborador=" + colaborador +
                '}';
    }

    // Metodo específico para convertir una lista de Documentos a una lista de Audiencia
    private static List<Audiencia> fromDocumentListAudiencia(List<Document> documents) {
        return documents.stream()
                .map(Audiencia::fromDocument)  // Llama al metodo fromDocument de Audiencia
                .collect(Collectors.toList());  // Convierte el stream en una lista
    }

    // Metodo específico para convertir una lista de Documentos a una lista de Colaborador
    private static List<Colaborador> fromDocumentListColaborador(List<Document> documents) {
        return documents.stream()
                .map(Colaborador::fromDocument)  // Llama al metodo fromDocument de Colaborador
                .collect(Collectors.toList());  // Convierte el stream en una lista
    }
}

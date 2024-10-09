import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

public class Marshalling {

    public static void marshallingXML() {
        try {
            Casetas casObj = new Casetas(casetaFeria.listarCasetas());

            // Fichero para serializar el objeto Caseta
            String xmlFilePath = "./src/main/java/casetas.xml";

            // Crear un contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            // Crear un marshaller para convertir el objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar el objeto a una cadena XML
            StringWriter sw = new StringWriter();
            marshaller.marshal(casObj, sw);
            String xmlString = sw.toString();

            // Serializar el objeto a un fichero
            marshaller.marshal(casObj, new File(xmlFilePath));

            // Mostrar el XML resultante
            System.out.println("Objeto Caseta serializado a XML:");
            System.out.println(xmlString);

        } catch (JAXBException e) {
            System.err.println("No se encuentra nada que Serializar.");
        }
    }

    public static void unMarshallingXML() {
        try {
            // Fichero para serializar el objeto Caseta
            String xmlFilePath = "./src/main/java/casetas.xml";

            // Crear un contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            // Deserializar objeto desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Casetas usuarioFromXml = (Casetas) unmarshaller.unmarshal(new File(xmlFilePath));

            // Mostramos el objeto recuperado del XML
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());

        } catch (JAXBException e) {
            System.err.println("El archivo no existe.");
        }
    }

    public static void casetaIdXML() {
        try {
            //Pedimos por pantalla la ID
            Scanner sc = new Scanner(System.in);
            System.out.println("Indique la id a buscar: ");
            int id = sc.nextInt();

            // Fichero para serializar el objeto Caseta
            String xmlFilePath = "./src/main/java/casetas.xml";

            // Crear un contexto JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            // Deserializar objeto desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Casetas usuarioFromXml = (Casetas) unmarshaller.unmarshal(new File(xmlFilePath));

            casetaFeria casetaBuscada = usuarioFromXml.getCasetas().stream().filter(c -> c.getId() == id).findFirst().orElse(null);

            //Mostramos el objeto recuperado del XML
            if (casetaBuscada == null) {
                System.out.println("No existe una caseta con esa ID.");
            } else {
                System.out.println(casetaBuscada);
            }
        } catch (JAXBException e) {
            System.err.println("El archivo de busqueda no existe.");
        }
    }

    public static void MarshalingJSON() {
        try {
            // Fichero para serializar el objeto casetas
            String jsonFilePath = "./src/main/java/casetas.json";
            Casetas casObj = new Casetas(casetaFeria.listarCasetas());

            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Serializar el objeto a una cadena JSON
            String jsonString = objectMapper.writeValueAsString(casObj);

            objectMapper.writeValue(new File(jsonFilePath), casObj);

            // Mostrar el JSON resultante
            System.out.println("Objeto Usuario serializado a JSON:");
            System.out.println(jsonString);

        } catch (IOException e) {
            System.err.println("No se encuentra nada que Serializar.");
        }
    }

    public static void unMarshallingJSON() {
        try {
            // Fichero para serializar el objeto casetas
            String jsonFilePath = "./src/main/java/casetas.json";

            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserializar el JSON a un objeto Usuario
            Casetas usuarioFromXml = objectMapper.readValue(new File(jsonFilePath), Casetas.class);

            // Mostramos el objeto recuperado del XML
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());

        } catch (IOException e) {
            System.err.println("El archivo no existe.");
        }
    }

    public static void casetaIdJSON() {
        try {
            //Pedimos por pantalla la ID
            Scanner sc = new Scanner(System.in);
            System.out.println("Indique la id a buscar: ");
            int id = sc.nextInt();

            // Fichero para serializar el objeto casetas
            String jsonFilePath = "./src/main/java/casetas.json";

            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserializar el JSON a un objeto Usuario
            Casetas usuarioFromXml = objectMapper.readValue(new File(jsonFilePath), Casetas.class);

            casetaFeria casetaBuscada = usuarioFromXml.getCasetas().stream().filter(c -> c.getId() == id).findFirst().orElse(null);

            // Mostramos el objeto recuperado del JSON
            if (casetaBuscada == null) {
                System.out.println("No existe una caseta con esa ID.");
            } else {
                System.out.println(casetaBuscada);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

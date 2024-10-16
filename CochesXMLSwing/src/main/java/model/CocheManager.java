package model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CocheManager {
        private static final String FILE_PATH = "./src/main/java/coches.xml";

        public void guardarCoches(List<Coche> coches) {
            try {
                JAXBContext context = JAXBContext.newInstance(CocheListWrapper.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(new CocheListWrapper(coches), new File(FILE_PATH));
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        public List<Coche> cargarCoches() {

            File file = new File(FILE_PATH);
            // Verificar si el archivo existe antes de intentar cargarlo
            if (!file.exists()) {
                System.out.println("El archivo no existe. Se retornará una lista vacía.");
                return new ArrayList<>(); // Retornar una lista vacía si el archivo no existe
            }

            try {
                JAXBContext context = JAXBContext.newInstance(CocheListWrapper.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                CocheListWrapper wrapper = (CocheListWrapper) unmarshaller.unmarshal(new File(FILE_PATH));
                return wrapper.getCoches();
            } catch (JAXBException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

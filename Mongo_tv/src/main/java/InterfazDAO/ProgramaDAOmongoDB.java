package InterfazDAO;

import Modelos.Audiencia;
import Modelos.Programa;
import Utils.Conexion;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAOmongoDB implements ProgramaDAO {

    Conexion conexion = new Conexion();

    private final MongoDatabase database = conexion.connection();

    private final MongoCollection<Document> coleccion = database.getCollection("Programas");

    public ProgramaDAOmongoDB() {
    }

    // Metodo para insertar un Programa en MongoDB
    public void guardarPrograma(Programa programa) {
        Document documento = programa.toDocument();
        coleccion.insertOne(documento);
    }

    // Metodo para obtener todos los Programas de MongoDB
    public List<Programa> obtenerProgramas() {
        List<Programa> programas = new ArrayList<>();

        for (Document documento : coleccion.find()) {
            programas.add(Programa.fromDocument(documento));
        }
        return programas;
    }

    //Metodo para borrar un programa por su nombre
    public boolean borrarProgramaPorNombre(String nombre) {
        try {
            Document filtro = new Document("nombre", nombre);

            var result = coleccion.deleteOne(filtro);

            // Si se ha eliminado al menos un documento, el borrado fue exitoso
            return result.getDeletedCount() > 0;
        } catch (MongoWriteException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Metodo para borrar un programa por su Id
    public boolean borrarProgramaPorId(String id) {
        try {
            Document filtro = new Document("_id", id);

            var result = coleccion.deleteOne(filtro);

            // Si se ha eliminado al menos un documento, el borrado fue exitoso
            return result.getDeletedCount() > 0;
        } catch (MongoWriteException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Actualizar un programa
    public boolean actualizarProgramaPorId(String id, Programa programaActualizado) {
        try {
            // Asegúrate de que el id sea convertido a ObjectId si es necesario.
            ObjectId objectId = new ObjectId(id);
            Document filtro = new Document("_id", objectId);

            // Convierte el objeto Programa a un Document
            Document actualizacion = new Document("$set", programaActualizado.toDocument());

            // Ejecutar la actualización en la base de datos
            var resultado = coleccion.updateOne(filtro, actualizacion);

            // Imprimir los resultados para diagnóstico
            System.out.println("Documentos modificados: " + resultado.getModifiedCount());

            // Retornar true si al menos un documento fue modificado
            return resultado.getModifiedCount() > 0;

        } catch (Exception e) {
            // Captura y muestra los errores, si los hay
            e.printStackTrace();
            return false;
        }
    }


    //Metodo para buscar un programa por su nombre
    public Programa buscarProgramaPornombre(String nombre) {
        try {
            Document filtro = new Document("nombre", nombre);

            var resultado = coleccion.find(filtro).first();

            return Programa.fromDocument(resultado);

        } catch (Exception e) {
            System.out.println("No se encontro ningun programa son ese nombre: " + e.getMessage());
        }
        return null;
    }

    //Metodo para buscar un programa por su Id.
    public Programa buscarProgramaPorId(String id) {
        try {
            Document filtro = new Document("_id", id);

            var resultado = coleccion.find(filtro).first();

            return Programa.fromDocument(resultado);

        } catch (Exception e) {
            System.out.println("No se encontro ningun programa son esa ID: " + e.getMessage());
        }
        return null;
    }

    //Metodo para listar los programas de una categoria.
    public List<Programa> listarProgramaPorCategoria(String categoria) {
        try {
            Document filtro = new Document("categoria", categoria);
            System.out.println("Filtro de búsqueda: " + filtro.toJson());

            var resultado = coleccion.find(filtro);
            List<Programa> programas = new ArrayList<>();

            for (Document documento : resultado) {
                System.out.println("Documento encontrado: " + documento.toJson());
                programas.add(Programa.fromDocument(documento));
            }

            return programas;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public Programa obtenerProgramaConMayorAudiencia(String fechaEspecifica) {
        try {
            List<Programa> programas = obtenerProgramas();  // Obtener todos los programas

            // Verificar si la lista de programas está vacía
            if (programas == null || programas.isEmpty()) {
                return null;  // Si no hay programas, devolver null
            }

            // Convertir la fecha especificada a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaEspecificaParsed = LocalDate.parse(fechaEspecifica, formatter);

            Programa pConMasEspectadores = null;
            int maxEspectadores = 0;

            // Recorremos la lista de programas verificando los espectadores.
            for (Programa programa : programas) {
                List<Audiencia> audiencias = programa.getAudiencia();

                if (audiencias != null && !audiencias.isEmpty()) {
                    for (Audiencia audiencia : audiencias) {
                        LocalDate fechaAudiencia = LocalDate.parse(audiencia.getFecha());

                        if (fechaAudiencia.isEqual(fechaEspecificaParsed)) {
                            int espectadores = audiencia.getEspectadores();
                            if (espectadores > maxEspectadores) {
                                maxEspectadores = espectadores;
                                pConMasEspectadores = programa;
                            }
                        }
                    }
                }
            }
            return pConMasEspectadores;

        } catch (Exception e) {
            // Añadir un mensaje más claro para la excepción
            throw new RuntimeException("Error al obtener el programa: " + fechaEspecifica, e);
        }
    }

    //Metodo para calcular la audiencia media en un rango de fechas.
    public int calcularAudienciaMedia(String id, String fechaInicio, String fechaFin) {
        try {
            // Convertir el id de String a ObjectId si es necesario
            ObjectId objectId = new ObjectId(id);  // Aquí se convierte el id en un ObjectId

            // Buscar el programa en la base de datos usando el _id
            Document filtro = new Document("_id", objectId);
            Document resultado = coleccion.find(filtro).first();

            // Verificar si el programa existe
            if (resultado == null) {
                System.out.println("No se encontró el programa con id: " + id);
                return 0;
            }

            // Convertir el documento del programa a un objeto Programa
            Programa programa = Programa.fromDocument(resultado);

            // Verificar si el programa tiene audiencias
            if (programa.getAudiencia() == null || programa.getAudiencia().isEmpty()) {
                System.out.println("No hay audiencias registradas para el programa: " + programa.getNombre());
                return 0;
            }

            // Convertir las fechas de inicio y fin a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaInicioParsed = LocalDate.parse(fechaInicio, formatter);
            LocalDate fechaFinParsed = LocalDate.parse(fechaFin, formatter);

            int totalEspectadores = 0;
            int audienciasEnRango = 0;

            // Recorremos las audiencias del programa y obtenemos la fecha de la audiencia
            for (Audiencia audiencia : programa.getAudiencia()) {
                // Asegurarnos de que la fecha en la audiencia se pueda convertir correctamente a LocalDate
                LocalDate fechaAudiencia;
                try {
                    fechaAudiencia = LocalDate.parse(audiencia.getFecha());
                } catch (Exception e) {
                    System.out.println("Error al parsear la fecha de la audiencia: " + audiencia.getFecha());
                    continue;
                }

                // Verificamos si la fecha de la audiencia está dentro del rango
                if ((fechaAudiencia.isEqual(fechaInicioParsed) || fechaAudiencia.isAfter(fechaInicioParsed)) &&
                        (fechaAudiencia.isEqual(fechaFinParsed) || fechaAudiencia.isBefore(fechaFinParsed))) {
                    totalEspectadores += audiencia.getEspectadores();
                    audienciasEnRango++;
                }
            }

            // Si no hay audiencias dentro del rango de fechas, devolvemos 0
            if (audienciasEnRango == 0) {
                System.out.println("No hay audiencias dentro del rango de fechas.");
                return 0;
            }

            // Calculamos la audiencia media
            int audienciaMedia = totalEspectadores / audienciasEnRango;
            System.out.println("Audiencia media calculada: " + audienciaMedia);

            return audienciaMedia;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}


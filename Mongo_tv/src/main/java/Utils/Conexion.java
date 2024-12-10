package Utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class Conexion {

    // Instancia de MongoClient a nivel de clase para mantenerla abierta durante la ejecución
    private MongoClient mongoClient = null;

    // Metodo para conectar a MongoDB y obtener la base de datos.
    public MongoDatabase connection() {
        MongoDatabase database = null;

        try {
            // Verifica si ya hay una conexión abierta
            if (mongoClient == null) {
                mongoClient = MongoClients.create("mongodb://localhost:27017");
                System.out.println("Conexión exitosa a MongoDB.");
            }

            // Obtener la base de datos
            database = mongoClient.getDatabase("Programas");
            System.out.println("Conexión exitosa a la base de datos 'Programas'.");

        } catch (Exception e) {
            System.err.println("Error al conectar a MongoDB: " + e.getMessage());
            e.printStackTrace();
        }

        return database;
    }

    // Metodo para cerrar la conexión
    public void closeConnection() {
        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Conexión cerrada exitosamente.");
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión a MongoDB: " + e.getMessage());
            }
        }
    }
}

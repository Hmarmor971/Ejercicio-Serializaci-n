package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseConnection {
    private static Connection connection = null;

    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties properties = new Properties();
            if (input == null) {
                System.out.println("No se encontró el archivo db.properties en el classpath.");

            }

            // Cargar las propiedades desde el archivo
            properties.load(input);

            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a la base de datos exitosa.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodo para obtener la conexión
    public static Connection getConnection() {
        return connection;
    }


    public static void createTriggers() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Eliminar el trigger si ya existe
            String sql = "DROP TRIGGER IF EXISTS `produccion_negativa`";
            statement.execute(sql);

            // Crear el trigger para verificar que la cantidad recolectada no sea negativa o cero
            sql = "CREATE TRIGGER `produccion_negativa` \n" +
                    "BEFORE INSERT ON `Produccion` \n" +
                    "FOR EACH ROW\n" +
                    "BEGIN\n" +
                    "    IF NEW.`cantidadRecolectada` <= 0 THEN\n" +
                    "        SIGNAL SQLSTATE '45000' \n" +
                    "        SET MESSAGE_TEXT = 'La cantidad recolectada no puede ser 0 o negativa'; \n" +
                    "    END IF;\n" +
                    "END";

            statement.execute(sql);

            System.out.println("Disparador creado correctamente para verificar la cantidad recolectada.");

        } catch (SQLException e) {
            System.out.println("Error al crear los disparadores. " + e.getMessage());
        }
    }

}
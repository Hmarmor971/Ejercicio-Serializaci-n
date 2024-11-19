package servicios;

import conexion.DatabaseConnection;
import entidades.Almazara;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlmazaraDAO {

    private Connection connection;

    //Constructor
    public AlmazaraDAO() {
        connection = DatabaseConnection.getConnection();
    }

    //Metodo para añadir una almazara a la base de datos
    public void addAlmazara(Almazara almazara) {
        String query = "INSERT (nombre, ubicacion, capacidad) INTO Almazara VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, almazara.getNombre());
            stmt.setString(2, almazara.getUbicacion());
            stmt.setDouble(3, almazara.getCapacidad());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodos para eliminar una Almazara
    public void deleteAlmazara(Almazara almazara) {
        String query = "DELETE FROM Almazara where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, almazara.getId());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void deleteAlmazaraById(int idAlmazara) {
        String query = "DELETE FROM Almazara where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAlmazara);
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void updateAlmazara(Almazara almazara) {
        String query = "UPDATE Almazara SET nombre = ?, ubicacion = ?, capacidad = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, almazara.getNombre());
            stmt.setString(2, almazara.getUbicacion());
            stmt.setDouble(3, almazara.getCapacidad());
            stmt.setInt(4, almazara.getId());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo que devuelve una almazara en funcion de la ID.
    public Almazara getAlmazaraById(int idAlmazara) {
        String query = "SELECT * FROM Almazara WHERE id = ?";
        Almazara almazara = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAlmazara);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                almazara = new Almazara(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDouble("capacidad")

                );
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return almazara;
    }

    //Metodo que devuelve una lista de todas las Almazaras.
    public List<Almazara> getAllAlmazaras() {
        String query = "SELECT * FROM Almazaras";

        List<Almazara> almazaras = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                almazaras.add(new Almazara(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("ubicacion"),
                        rs.getDouble("capacidad")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return almazaras;
    }
}

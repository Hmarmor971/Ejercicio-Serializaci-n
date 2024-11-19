package servicios;

import conexion.DatabaseConnection;
import entidades.Cuadrilla;
import entidades.Olivar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OlivarDAO {
    private Connection connection;

    //Constructor
    public OlivarDAO() {
        connection = DatabaseConnection.getConnection();
    }

    //Metodo para añadir un Olivar a la base de datos.
    public void addOlivar(Olivar olivar) {
        String query = "INSERT INTO Olivar (id, ubicacion, hectareas, produccionAnual) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, olivar.getId());
            stmt.setString(2, olivar.getUbicacion());
            stmt.setDouble(3, olivar.getHectareas());
            stmt.setDouble(4, olivar.getProduccionAnual());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodos para borrar un Olivar de la base de datos.
    public void deleteOlivar(Olivar olivar) {
        String query = "DELETE from Olivar where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, olivar.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void deleteOlivarById(int idOlivar) {
        String query = "DELETE from Olivar where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idOlivar);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }


    //Actualizar un Olivar
    public void updateOlivar(Olivar olivar) {
        String query = "UPDATE Olivar SET ubicacion = ?, hectareas = ?, produccionAnual = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, olivar.getId());
            stmt.setString(2, olivar.getUbicacion());
            stmt.setDouble(3, olivar.getHectareas());
            stmt.setDouble(4, olivar.getProduccionAnual());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo para buscar un Olivar por si Id.
    public Olivar getOlivarById(int idCuadrilla) {
        String query = "SELECT * FROM Olivar WHERE id = ?";
        Olivar olivar = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                olivar = new Olivar(
                        rs.getInt("id"),
                        rs.getString("ubicacion"),
                        rs.getDouble("hectareas"),
                        rs.getDouble("produccionAnual")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return olivar;
    }


    //Metodo que devuelve una lista de todos los olivares.
    public List<Olivar> getAllOlivares() {
        String query = "SELECT * FROM Olivar";
        List<Olivar> olivares = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                olivares.add(new Olivar(
                        rs.getInt("id"),
                        rs.getString("ubicacion"),
                        rs.getDouble("hectareas"),
                        rs.getDouble("produccionAnual")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return olivares;
    }


    //Metodo para asignar una cuadrilla a un olivar.
    public void asignarCuadrilla(int idCuadrilla, int idOlivar) {
        String query = "INSERT (cuadrilla_id, olivar_id) INTO Cuadrilla_Olivar VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            stmt.setInt(2, idOlivar);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo que devuelve todas las cuadrillas de una Olivar.
    public List<Cuadrilla> getCuadrillasByOlivarId(int idOlivar) {
        List<Cuadrilla> cuadrillas = new ArrayList<>();
        String query = "SELECT * from Cuadrillas a INNER JOIN Cuadrilla_Olivar b ON a.id=b.cuadrilla_id where olivar_id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idOlivar);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuadrillas.add(new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return cuadrillas;
    }
}

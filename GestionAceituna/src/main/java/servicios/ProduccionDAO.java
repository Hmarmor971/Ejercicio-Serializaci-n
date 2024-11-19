package servicios;

import conexion.DatabaseConnection;
import entidades.Produccion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProduccionDAO {

    private Connection connection;

    //Constructor
    public ProduccionDAO() {
        connection = DatabaseConnection.getConnection();
    }

    //Metodo para añadir una produccion a la base de datos
    public void addProduccion(Produccion produccion) {
        String query = "INSERT (cuadrilla_id, olivar_id, almazara_id, fecha, cantidadRecolectada) INTO Produccion VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, produccion.getCuadrilla_id());
            stmt.setInt(2, produccion.getOlivar_id());
            stmt.setDouble(3, produccion.getAlmazada_id());
            stmt.setDate(4, Date.valueOf(produccion.getFecha()));
            stmt.setDouble(5, produccion.getCantidadRecolectada());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodos para eliminar una Produccion
    public void deleteProduccion(Produccion produccion) {
        String query = "DELETE FROM Produccion where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, produccion.getId());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void deleteProduccionById(int idProduccion) {
        String query = "DELETE FROM Produccion where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProduccion);
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo para actualizar una produccion
    public void updateProduccion(Produccion produccion) {
        String query = "UPDATE Produccion SET cuadrilla_id = ?, olivar_id = ?, almazara_id = ?, fecha = ?, cantidadRecolectada = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, produccion.getCuadrilla_id());
            stmt.setInt(2, produccion.getOlivar_id());
            stmt.setInt(3, produccion.getAlmazada_id());
            stmt.setDate(4, Date.valueOf(produccion.getFecha()));
            stmt.setDouble(5, produccion.getCantidadRecolectada());
            stmt.setInt(6, produccion.getId());
            stmt.executeQuery();

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }


    //Metodo que devuelve una Produccion en funcion de la ID.
    public Produccion getProduccionById(int idProduccion) {
        String query = "SELECT * FROM Almazara WHERE id = ?";
        Produccion produccion = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProduccion);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produccion = new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almzara_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("cantidadRecolectada")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return produccion;
    }

    //Metodo que devuelve una lista de las producciones
    public List<Produccion> getAllProduccion() {
        String query = "SELECT * FROM Almazara";
        List<Produccion> producciones = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                producciones.add(new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almzara_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("cantidadRecolectada")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return producciones;
    }


    //Metodo para mostrar la produccion de una fecha concreta en funcion de la id de la cuadrilla y de la almazara.
    public List<Produccion> getAllProduccionByFechaCuadrillaAlmazara(LocalDate date, int idCuadrilla, int idAlmazara) {
        String query = "SELECT * FROM Produccion where cuadrilla_id = ? and almazara_id = ? and fecha = ?";
        List<Produccion> produccion = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            stmt.setInt(2, idAlmazara);
            stmt.setDate(3, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produccion.add(new Produccion(
                        rs.getInt("id"),
                        rs.getInt("cuadrilla_id"),
                        rs.getInt("olivar_id"),
                        rs.getInt("almazara_id"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getDouble("cantidadRecolectada")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return produccion;

    }

    //Metodo que da la produccion hasta una fecha concreta de una almazara.
    public int getAllAlmazaraProduccionUntilFecha(LocalDate date, int idAlmazara) {
        String query = "SELECT SUM(cantidadRecolectada) FROM Produccion where almazara_id = ? and fecha <= ?";
        int produccion = 0;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idAlmazara);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produccion = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return produccion;

    }

    //Metodo que da la produccion hasta una fecha concreta de un olivar.
    public int getAllOlivarProduccionUntilFecha(LocalDate date, int idOlivar) {
        String query = "SELECT SUM(cantidadRecolectada) FROM Produccion where olivar_id = ? and fecha <= ?";
        int produccion = 0;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idOlivar);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produccion = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return produccion;

    }

    //Metodo que da la produccion hasta una fecha concreta de un cuadrilla.
    public int getAllCuadrillaProduccionUntilFecha(LocalDate date, int idCuadrilla) {
        String query = "SELECT SUM(cantidadRecolectada) FROM Produccion where cuadrilla_id = ? and fecha <= ?";
        int produccion = 0;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produccion = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return produccion;

    }
}

package servicios;

import conexion.DatabaseConnection;
import entidades.Cuadrilla;
import entidades.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabajadorDAO {

    private Connection connection;

    //Constructor
    public TabajadorDAO() {
        connection = DatabaseConnection.getConnection();
    }


    //Metodo para añadir un trabajador a la base de datos.
    public void addTrabajador(Trabajador trabajador) {
        String query = "INSERT INTO Trabajador (id, nombre, edad, puesto, salario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, trabajador.getId());
            stmt.setString(2, trabajador.getNombre());
            stmt.setInt(3, trabajador.getEdad());
            stmt.setString(4, trabajador.getPuesto());
            stmt.setDouble(5, trabajador.getSalario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodos para borrar un trabajador de la base de datos.
    public void deleteTrabajador(Trabajador trabajador) {
        String query = "DELETE from Trabajador where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, trabajador.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void deleteTrabajadorById(int idTrabajador) {
        String query = "DELETE from Trabajador where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }


    //Actualizar un Trabajador de la base de datos.
    public void updateTrabajador(Trabajador trabajador) {
        String query = "UPDATE Trabajador SET nombre = ?, edad = ?, puesto = ?, salario = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(5, trabajador.getId());
            stmt.setString(1, trabajador.getNombre());
            stmt.setInt(2, trabajador.getEdad());
            stmt.setString(3, trabajador.getPuesto());
            stmt.setDouble(4, trabajador.getSalario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo para buscar un Trabajador por si Id.
    public Trabajador getTrabajadorById(int idTrabajador) {
        String query = "SELECT * FROM Trabajador WHERE id = ?";
        Trabajador trabajador = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                trabajador = new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("puesto"),
                        rs.getDouble("salario")

                );
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return trabajador;
    }


    //Metodo que devuelve una lista de todos los trabajadores.
    public List<Trabajador> getAllTrabajadores() {
        String query = "SELECT * FROM Trabajador";
        List<Trabajador> trabajadores = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                trabajadores.add(new Trabajador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("edad"),
                        rs.getString("puesto"),
                        rs.getDouble("salario")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return trabajadores;
    }


    //Metodo que devuelve todas las cuadrillas donde esta el trabajador.
    public List<Cuadrilla> getCuadrillaByTrabajadorId(int idTrabajador) {
        List<Cuadrilla> cuadrillas = new ArrayList<>();

        String query = "SELECT * from Cuadrilla a INNER JOIN Cuadrilla_Trabajador b ON a.id=b.cuadrilla_id where trabajador_id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuadrillas.add(new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("supervidor_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return cuadrillas;
    }

    //Metodo asignar cuadrillas
    public void asignarCuadrilla(int idTrabajador, int idCuadrilla) {
        String query = "INSERT (trabajador_id, cuadrilla_id) INTO Cuadrilla_Trabajador VALUES(?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            stmt.setInt(2, idCuadrilla);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }
}




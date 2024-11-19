package servicios;

import conexion.DatabaseConnection;
import entidades.Almazara;
import entidades.Cuadrilla;
import entidades.Olivar;
import entidades.Trabajador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuadrillaDAO {

    private Connection connection;

    //Constructor
    public CuadrillaDAO() {
        connection = DatabaseConnection.getConnection();
    }

    //Metodo para añadir una Cuadrilla a la base de datos.
    public void addCuadrilla(Cuadrilla cuadrilla) {
        String query = "INSERT INTO Cuadrilla (id, nombre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cuadrilla.getId());
            stmt.setString(2, cuadrilla.getNombre());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodos para borrar un Olivar de la base de datos.
    public void deleteCuadrilla(Cuadrilla cuadrilla) {
        String query = "DELETE from Cuadrilla where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cuadrilla.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    public void deleteCuadrillaById(int idCuadrilla) {
        String query = "DELETE from Cuadrilla where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }


    //Actualizar un Trabajador de la base de datos.
    public void updateCuadrilla(Cuadrilla cuadrilla) {
        String query = "UPDATE Cuadrilla SET nombre = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(5, cuadrilla.getId());
            stmt.setString(1, cuadrilla.getNombre());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo para buscar una Cuadrilla por si Id.
    public Cuadrilla getCuadrillaById(int idCuadrilla) {
        String query = "SELECT * FROM Cuadrilla WHERE id = ?";
        Cuadrilla cuadrilla = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cuadrilla = new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return cuadrilla;
    }

    //Metodo que devuelve una lista de todas las cuadrillas.
    public List<Cuadrilla> getAllCuadrillas() {
        String query = "SELECT * FROM Cuadrilla";
        List<Cuadrilla> cuadrillas = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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


    //Metodo que devuelve todas los trabajadores de una cuadrilla.
    public List<Trabajador> getTrabajadoresByCuadrillaId(int idCuadrilla) {
        List<Trabajador> cuadrillas = new ArrayList<>();
        String query = "SELECT * from Trabajador a INNER JOIN Cuadrilla_Trabajador b ON a.id=b.trabajador_id where cuadrilla_id = ? ";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuadrillas.add(new Trabajador(
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
        return cuadrillas;
    }

    //Metodo para asignar un trabajador como supervisor.
    public void asignarSupervisor(int idTrabajador, int idCuadrilla) {
        String query = "UPDATE Cuadrilla SET supervisor_id = ? where id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            stmt.setInt(2, idCuadrilla);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo para listar todos los supervisores.
    public List<Trabajador> allSupervisores() {
        List<Trabajador> trabajadores = new ArrayList<>();
        String query = "SELECT * from Trabajador a INNER JOIN Cuadrilla_Trabajador b ON a.id=b.trabajdor_id INNER JOIN Cuadrilla c ON b.cuadrilla_id=c.id where a.id in (Select supervisor_id from Cuadrilla)";

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

    //Metodo para conseguir el supervisor de una cuadrilla
    public Trabajador getSupervisorByCuadrillaId(int idCuadrilla) {
        Trabajador supervisor = null;
        String query = "SELECT * from Trabajador a INNER JOIN Cuadrilla_Trabajador b ON a.id=b.trabajdor_id INNER JOIN Cuadrilla c ON b.cuadrilla_id=c.id where a.id in (Select supervisor_id from Cuadrilla where id = ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            ResultSet rs = stmt.executeQuery();

            supervisor = new Trabajador(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    rs.getString("puesto"),
                    rs.getDouble("salario")
            );

        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
        return supervisor;
    }


    //Metodo para obtener la cuadrilla de un determinado supervisor.
    public List<Cuadrilla> getCuadrillaBySupervisorID(int idSupervisor) {
        String query = "SELECT * FROM Cuadrilla where supervisor_id = ? ";

        List<Cuadrilla> cuadrillas = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idSupervisor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cuadrillas.add(new Cuadrilla(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("supervisor_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error: El trabajador indicado no supervisa ninguna cuadrilla.");
        }
        return cuadrillas;
    }

    //Metodo para asignar un Trabajador a la cuadrilla.
    public void asignarTrabajador(int idTrabajador, int idCuadrilla) {
        String query = "INSERT (trabajador_id, cuadrilla_id) INTO Cuadrilla_Trabajador VALUES(?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idTrabajador);
            stmt.setInt(2, idCuadrilla);
            stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para asignar Olivar a la cuadrilla.
    public void asignarOlivar(int idCuadrilla, int idOlivar) {
        String query = "INSERT (cuadrilla_id, olivar_id) INTO Cuadrilla_Olivar VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
            stmt.setInt(2, idOlivar);
            stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
        }
    }

    //Metodo que devuelve una lista de todos los olivares de una cuadrilla.
    public List<Olivar> getAllOlivaresByCuadrillaId(int idCuadrilla) {
        String query = "SELECT * from Olivar a INNER JOIN Cuadrilla_Olivar b ON a.id=b.olivar_id where cuadrilla_id = ? ";
        List<Olivar> olivares = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
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

    //Metodo para listar las Almazaras donde lleva su produccion cierta cuadrilla.
    public List<Almazara> getAlmazarasByCuadrillaId(int idCuadrilla) {
        String query = "SELECT DISTINCT(a.id), a.nombre, a.ubicacion, a.capacidad from Almazara a INNER JOIN Produccion b ON a.id=b.almazara_id where b.cuadrilla_id = ? ";
        List<Almazara> almazaras = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCuadrilla);
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

package Main;

import Marshalling.JsonMarshalling;
import Marshalling.JsonUnmarshalling;
import Marshalling.XmlMarshalling;
import Marshalling.XmlUnmarshalling;
import conexion.DatabaseConnection;
import entidades.*;
import servicios.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        TabajadorDAO tabajadorDAO = new TabajadorDAO();
        CuadrillaDAO cuadrillaDAO = new CuadrillaDAO();
        OlivarDAO olivarDAO = new OlivarDAO();
        ProduccionDAO produccionDAO = new ProduccionDAO();

        int id, id2;
        LocalDate fecha;


        while (true) {
            mostrarMenu();
            int opcion = sc.nextInt();
            sc.nextLine();  // Limpiar el buffer de entrada
            switch (opcion) {
                case 1:
                    //Mostrar los trabajadores de una determinada cuadrilla.
                    try {
                        System.out.println("Indica la ID de la cuadrilla.");
                        id = sc.nextInt();
                        System.out.println("Trabajadores de la cuadrilla " + id);
                        List<Trabajador> trabajadores = cuadrillaDAO.getTrabajadoresByCuadrillaId(id);
                        for (Trabajador T : trabajadores) {
                            System.out.printf("Id: %d\nNombre: %s\nEdad: %d\nPuesto: %s\nSalario: %.2f\n\n",
                                    T.getId(),
                                    T.getNombre(),
                                    T.getEdad(),
                                    T.getPuesto(),
                                    T.getSalario());
                        }
                    } catch (NullPointerException e) {
                        System.err.println("Error, La cuadrilla indicada no existe o no tiene trabajadores asignados: " + e.getMessage());
                    }
                    break;
                case 2:
                    //Mostrar las cuadrillas que supervisa un determinado trabajador.
                    try {
                        System.out.println("Indica la ID del supervisor.");
                        id = sc.nextInt();
                        List<Cuadrilla> cuadrillas = cuadrillaDAO.getCuadrillaBySupervisorID(id);
                        System.out.print("La cuadrilla correspondiente a ese supervisor son: ");
                        for (Cuadrilla C : cuadrillas) {
                            System.out.printf("\nID: %d\nNombre: %s", C.getId(), C.getNombre());
                        }
                    } catch (NullPointerException e) {
                        System.err.println("Error: El trabajador indicado no supervisa ninguna cuadrilla o no existe.");
                    }
                    break;
                case 3:
                    //Mostrar los olivares donde trabaja una determinada cuadrilla.
                    try {
                        System.out.println("Indica la ID de la cuadrilla.");
                        id = sc.nextInt();
                        List<Olivar> olivares = cuadrillaDAO.getAllOlivaresByCuadrillaId(id);
                        System.out.print("Olivares en los que trabaja la cuadrilla con id " + id);
                        for (Olivar O : olivares) {
                            System.out.printf("\nID: %d\nUbicación: %s\nHectáreas: %.2f\nProducción Anual: %.2f",
                                    O.getId(),
                                    O.getUbicacion(),
                                    O.getHectareas(),
                                    O.getProduccionAnual());
                        }
                    } catch (NullPointerException e) {
                        System.err.println("Error, La cuadrilla indicada no existe o no esta asiganada a ningun olivar: " + e.getMessage());
                    }
                    break;
                case 4:
                    //Mostrar las cuadrillas que trabajan en un determinado olivar.
                    try {
                        System.out.println("Indica la ID del olivar.");
                        id = sc.nextInt();
                        List<Cuadrilla> cuadrillas = olivarDAO.getCuadrillasByOlivarId(id);
                        System.out.print("La cuadrilla correspondiente a ese olivar son: ");
                        for (Cuadrilla C : cuadrillas) {
                            System.out.printf("%s%d%s%s", "\nID: ", C.getId(), "\nNombre: ", C.getNombre());
                        }
                    } catch (NullPointerException e) {
                        System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
                    }
                    break;
                case 5:
                    //Mostrar las almazaras donde lleva aceituna una determinada cuadrilla.
                    try {
                        System.out.println("Indica la ID de la cuadrilla.");
                        id = sc.nextInt();
                        List<Almazara> almazaras = cuadrillaDAO.getAlmazarasByCuadrillaId(id);
                        System.out.print("Olivares en los que trabaja la cuadrilla con id " + id);
                        for (Almazara A : almazaras) {
                            System.out.printf("\nID: %d\nNombre: %s\nUbicación: %s\nCapacidad: %.2f",
                                    A.getId(),
                                    A.getNombre(),
                                    A.getUbicacion(),
                                    A.getCapacidad());
                        }
                    } catch (NullPointerException e) {
                        System.err.println("Error, La cuadrilla indicada no existe o no tiene produccion: " + e.getMessage());
                    }
                    break;
                case 6:
                    //Mostrar la producción en una fecha concreta, de una cuadrilla concreta en una almazara concreta.
                    try {
                        System.out.println("Indica la fecha.");
                        fecha = LocalDate.parse(sc.next());
                        System.out.println("Indica la ID de la cuadrilla.");
                        id = sc.nextInt();
                        System.out.println("Indica la ID de la Almazara.");
                        id2 = sc.nextInt();
                        List<Produccion> produccion = produccionDAO.getAllProduccionByFechaCuadrillaAlmazara(fecha, id, id2);
                        System.out.print("Produccion correspondiente:");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        for (Produccion P : produccion) {
                            System.out.printf("\nID: %d\nCuadrilla: %d\nOlivar: %d\nAlmazara: %d\nFecha: %s\nCantidad Recolectada: %.2f",
                                    P.getId(),
                                    P.getCuadrilla_id(),
                                    P.getOlivar_id(),
                                    P.getAlmazada_id(),
                                    P.getFecha().format(formatter),
                                    P.getCantidadRecolectada());
                        }
                    } catch (Exception e) {
                        System.err.println("Error, datos proporcionados incorrectos o faltantes: " + e.getMessage());
                    }
                    break;
                case 7:
                    //Mostrar la producción hasta una determinada fecha, de una determinada almazara.
                    try {
                        System.out.println("Indica la fecha.");
                        fecha = LocalDate.parse(sc.next());
                        System.out.println("Indica la ID de la Almazara.");
                        id2 = sc.nextInt();
                        System.out.print("Produccion correspondiente: ");
                        int produccionTotal = produccionDAO.getAllAlmazaraProduccionUntilFecha(fecha, id2);
                        System.out.println(produccionTotal);
                    } catch (Exception e) {
                        System.err.println("Error al manejar los datos de la base de datos: " + e.getMessage());
                    }
                    break;
                case 8:
                    //Mostrar la producción hasta una determinada fecha, de un determinado olivar.
                    // (en todas las almazaras y de todas las cuadrillas que trabajan allí).
                    try {
                        System.out.println("Indica la fecha.");
                        fecha = LocalDate.parse(sc.next());
                        System.out.println("Indica la ID de la Olivar.");
                        id2 = sc.nextInt();
                        System.out.print("Produccion correspondiente: ");
                        int produccionTotal = produccionDAO.getAllOlivarProduccionUntilFecha(fecha, id2);
                        System.out.println(produccionTotal);
                    } catch (Exception e) {
                        System.err.println("Error, datos proporcionados incorrectos o faltantes: " + e.getMessage());
                    }
                    break;
                case 9:
                    //Mostrar la producción hasta una determinada fecha, de una cuadrilla determinada
                    // (en todas las almazaras y de todos los olivares en los que trabaja dicha cuadrilla).
                    try {
                        System.out.println("Indica la fecha.");
                        fecha = LocalDate.parse(sc.next());
                        System.out.println("Indica la ID de la Cuadrilla.");
                        id2 = sc.nextInt();
                        System.out.print("Produccion correspondiente: ");
                        int produccionTotal = produccionDAO.getAllCuadrillaProduccionUntilFecha(fecha, id2);
                        System.out.println(produccionTotal);
                    } catch (Exception e) {
                        System.err.println("Error, datos proporcionados incorrectos o faltantes: " + e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        XmlMarshalling xmlMarshalling = new XmlMarshalling();
                        xmlMarshalling.xmlMarshalling();
                        System.out.println("Se ha creado el archivo XML");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 11:
                    try {
                        XmlUnmarshalling xmlUnmarshalling = new XmlUnmarshalling();
                        xmlUnmarshalling.xmlUnmarshalling();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 12:
                    try {
                        JsonMarshalling jsonMarshalling = new JsonMarshalling();
                        jsonMarshalling.jsonMarshalling();
                        System.out.println("Se ha creado el archivo JSON");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 13:
                    try {
                        JsonUnmarshalling jsonUnmarshalling = new JsonUnmarshalling();
                        jsonUnmarshalling.jsonUnmarshalling();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 14:
                    DatabaseConnection.createTriggers();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.print("""
                \n
                Seleccione una opción:
                1. Mostrar los trabajadores de una determinada cuadrilla.
                2. Mostrar las cuadrillas que supervisa un determinado trabajador.
                3. Mostrar los olivares donde trabaja una determinada cuadrilla.
                4. Mostrar las cuadrillas que trabajan en un determinado olivar.
                5. Mostrar las almazaras donde lleva aceituna una determinada cuadrilla.
                6. Mostrar la producción en una fecha concreta, de una cuadrilla concreta en una almazara concreta.
                7. Mostrar la producción hasta una determinada fecha, de una determinada almazara.
                8. Mostrar la producción hasta una determinada fecha, de un determinado olivar.
                9. Mostrar la producción hasta una determinada fecha, de una cuadrilla determinada.
                10. Serializar la base de datos a un archivo XML.
                11. Deserializar la base de datos guardada en un archivo XML.
                12. Serializar la base de datos a un archivo JSON.
                13. Deserializar la base de datos guardada en un archivo JSON.
                14. Crear trigger para que la produccion no pueda ingresar una cantidad negativa o 0.
                0. Salir
                Opción:\s""");
    }
}

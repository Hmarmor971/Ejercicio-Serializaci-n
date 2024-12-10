package Principal;

import InterfazDAO.ProgramaDAOmongoDB;
import Modelos.Audiencia;
import Modelos.Colaborador;
import Modelos.Horario;
import Modelos.Programa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProgramaDAOmongoDB programaDAO = new ProgramaDAOmongoDB();
        Scanner sc = new Scanner(System.in);

        int opcion = -1;
        String valor;
        int valorInt;

        while (opcion != 0) {
            // Mostrar las opciones del menú
            System.out.println("\n---- Menú de Programas ----");
            System.out.println("1. Crear un nuevo programa");
            System.out.println("2. Listar todos los programas");
            System.out.println("3. Consultar un programa por nombre");
            System.out.println("4. Actualizar un programa");
            System.out.println("5. Eliminar un programa");
            System.out.println("6. Listar programas de una categoría específica");
            System.out.println("7. Obtener el programa con mayor audiencia en una fecha concreta");
            System.out.println("8. Calcular la audiencia media de un programa en un rango de fechas");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            String id;
            switch (opcion) {
                case 1:
                    programaDAO.guardarPrograma(newPrograma());
                    break;

                case 2:
                    List<Programa> programas = programaDAO.obtenerProgramas();
                    for (Programa p : programas) {
                        System.out.println(p.toDocument().toJson());
                    }
                    break;

                case 3:
//                    System.out.print("Id del programa: ");
//                    String id = sc.nextLine();
//                    System.out.println(programaDAO.buscarProgramaPorId(id).toDocument().toJson());
                    System.out.print("Nombre del programa: ");
                    String nombre = sc.nextLine();
                    System.out.println(programaDAO.buscarProgramaPornombre(nombre).toDocument().toJson());
                    break;

                case 4:
                    System.out.print("Id del programa: ");
                    id = sc.nextLine();
                    programaDAO.actualizarProgramaPorId(id, newPrograma());
                    break;

                case 5:
                    System.out.print("Nombre del programa: ");
                    nombre = sc.nextLine();
                    programaDAO.borrarProgramaPorNombre(nombre);
                    break;

                case 6:
                    System.out.print("Nombre de la categoria: ");
                    nombre = sc.nextLine();
                    programaDAO.listarProgramaPorCategoria(nombre);
                    break;

                case 7:
                    System.out.print("Indica la fecha del programa: ");
                    String fecha = sc.nextLine();
                    System.out.println(programaDAO.obtenerProgramaConMayorAudiencia(fecha).toDocument().toJson());
                    break;

                case 8:
                    System.out.print("Indica la fecha del programa: ");
                    String fechaInicio = sc.nextLine();
                    System.out.print("Indica la fecha de fin del programa: ");
                    String fechaFin = sc.nextLine();
                    System.out.print("Id del programa: ");
                    id = sc.nextLine();
                    programaDAO.calcularAudienciaMedia(id, fechaInicio, fechaFin);
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción válida.");
            }


        }
    }

    //Metodo para pedir todos los datos de un programa.
    public static Programa newPrograma() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese los datos del nuevo programa:");

        System.out.print("Nombre del programa: ");
        String nombre = sc.nextLine();

        System.out.print("Categoría del programa: ");
        String categoria = sc.nextLine();

        System.out.print("Día de la semana: ");
        String dia = sc.nextLine();

        System.out.print("Hora (HH:mm): ");
        String hora = sc.nextLine();
        Horario horario = new Horario(dia, hora);

        List<Audiencia> audiencia = new ArrayList<>();
        System.out.print("Número de días emitidos: ");
        int numAudiencia = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numAudiencia; i++) {
            System.out.println("Ingrese los datos de la audiencia " + (i + 1) + ":");

            String fecha = null;
            boolean fechaValida = false;
            while (!fechaValida) {
                try {
                    System.out.print("Fecha (YYYY-MM-dd): ");
                    fecha = sc.nextLine();
                    fechaValida = true;
                } catch (Exception e) {
                    System.out.println("Fecha inválida. Asegúrese de usar el formato correcto (YYYY-MM-dd).");
                }
            }

            int espectadores = 0;
            boolean espectadoresValidos = false;
            while (!espectadoresValidos) {
                System.out.print("Número de espectadores: ");
                try {
                    espectadores = sc.nextInt();
                    if (espectadores >= 0) {
                        espectadoresValidos = true;
                    } else {
                        System.out.println("El número de espectadores no puede ser negativo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido de espectadores.");
                    sc.nextLine();
                }
            }
            audiencia.add(new Audiencia(fecha, espectadores));
        }

        List<Colaborador> colaboradores = new ArrayList<>();
        System.out.print("Número de colaboradores: ");
        int numColaboradores = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numColaboradores; i++) {
            System.out.println("Ingrese los datos del colaborador " + (i + 1) + ":");

            System.out.print("Nombre del colaborador: ");
            String nombreColaborador = sc.nextLine();
            sc.nextLine();

            System.out.print("Rol del colaborador: ");
            String rol = sc.nextLine();

            colaboradores.add(new Colaborador(nombreColaborador, rol));
        }

        return new Programa(nombre, categoria, horario, audiencia, colaboradores);
    }
}

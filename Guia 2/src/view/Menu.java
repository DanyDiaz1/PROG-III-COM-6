package view;

import config.ConexionMySQL;
import controller.AlumnoController;
import controller.DireccionController;
import model.Alumno;
import model.AlumnoDAO;
import model.Direccion;
import model.DireccionDAO;

import java.util.Scanner;

public class Menu {

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        AlumnoController alumnoController = new AlumnoController();
        DireccionController direccionController = new DireccionController();

        int opcion;
        do {
            System.out.println("1. Insertar alumno");
            System.out.println("2. Insertar dirección");
            System.out.println("3. Listar alumnos");
            System.out.println("4. Listar direcciones por alumno");
            System.out.println("5. Actualizar edad de un alumno");
            System.out.println("6. Eliminar un alumno");
            System.out.println("7. Eliminar una dirección");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese nombre, apellido, edad y email del alumno:");
                    String nombre = scanner.nextLine();
                    String apellido = scanner.nextLine();
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    String email = scanner.nextLine();
                    alumnoController.agregarAlumno(nombre,apellido,edad,email);
                    break;
                case 2:
                    System.out.println("Ingrese calle, altura e ID del alumno:");
                    String calle = scanner.nextLine();
                    int altura = scanner.nextInt();
                    int alumnoId = scanner.nextInt();
                    direccionController.agregarDireccion(calle,altura,alumnoId);
                    break;

                case 3:
                    alumnoController.obtenerAlumnos().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Ingrese ID del alumno:");
                    int idBuscar = scanner.nextInt();
                    direccionController.obtenerDireccionesPorAlumno(idBuscar).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Ingrese ID del alumno y nueva edad:");
                    alumnoController.actualizarEdad(scanner.nextInt(), scanner.nextInt());
                    break;
                case 6:
                    alumnoController.eliminarAlumno(scanner.nextInt());
                    break;
                case 7:
                    direccionController.eliminarDireccion(scanner.nextInt());
                    break;
                case 0:
                    System.out.println("Fin del programa");
                    break;
            }
        } while (opcion != 0);
        scanner.close();
        ConexionMySQL.cerrarConexion();
    }
}

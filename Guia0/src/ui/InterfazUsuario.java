package ui;

import excepciones.IdentificadorDuplicadoException;
import modelo.Expansion;
import modelo.Media;
import modelo.Videojuego;
import repositorios.IRepositorio;
import repositorios.RepositorioColeccion;

import java.util.Date;
import java.util.Scanner;

public class InterfazUsuario {
    private final Scanner scanner = new Scanner(System.in);
    private final IRepositorio<Media> repositorio = new RepositorioColeccion<>();

    public void iniciar() {
        while (true) {
            System.out.println("1. Agregar Videojuego");
            System.out.println("2. Agregar Expansión");
            System.out.println("3. Mostrar colección");
            System.out.println("4. Buscar por Genero");
            System.out.println("5. Eliminar por ID");
            System.out.println("6. Actualizar un atributo");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarVideojuego();
                    break;
                case 2:
                    agregarExpansion();
                    break;
                case 3:
                    mostrarColeccion();
                    break;
                case 4:
                    buscarPorGenero();
                    break;
                case 5:
                    eliminarPorId();
                    break;
                case 6:
                    actualizarAtributo();
                    break;
                case 7:
                    return;
            }
        }
    }

    private void agregarVideojuego() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Creador: ");
        String creador = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Versión: ");
        int version = scanner.nextInt();
        scanner.nextLine();

        try {
            repositorio.agregar(new Videojuego(titulo, creador, genero, id, version));
        } catch (IdentificadorDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void agregarExpansion() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Creador: ");
        String creador = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("ID: ");
        String id = scanner.nextLine();
        try {
            repositorio.agregar(new Expansion(titulo, creador, genero, id, new Date()));
        } catch (IdentificadorDuplicadoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarColeccion() {
        repositorio.obtenerTodos().forEach(System.out::println);
    }

    private void buscarPorGenero() {
        System.out.print("Ingrese ID: ");
        String genero = scanner.nextLine();
        Media media = repositorio.buscarPorGenero(genero);
        System.out.println(media != null ? media : "No encontrado");
    }

    private void eliminarPorId() {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        repositorio.eliminar(id);
    }

    private void actualizarAtributo(){
        System.out.println("Ingrese el ID del registro que desea modificar:");
        String id= scanner.nextLine();
        System.out.println("Ingresa el nombre del campo que desea modificar:");
        String campo = scanner.nextLine();
        System.out.println("Ingresa el valor nuevo:");
        String valor = scanner.nextLine();
        if (repositorio.actualizarAtributo(id,campo,valor)){
            System.out.println("El campo se modifico correctamente");
        }else {
            System.out.println("Ocurrio un error");
        }
    }
}

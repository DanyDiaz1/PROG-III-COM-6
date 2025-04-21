package view;

import java.util.Scanner;

public class MenuController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. CRUD de bebidas");
            System.out.println("2. Filtrar bebidas por tipo");
            System.out.println("3. Filtrar bebidas segun stock");
            System.out.println("4. Buscar clientes por localidad");
            System.out.println("5. Listar pedidos por cliente");
            System.out.println("6. Cambiar el estado de un pedido");
            System.out.println("7. Ingresar un nuevo pedido con validaciones");
            System.out.println("8. Contar la cantidad de pedidos agrupados por estado");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Que operacion desea realizar?");
                    System.out.println("A - Insertar una bebida nueva");
                    System.out.println("B - Listar todas las bebidas");
                    System.out.println("C - Actualizar una bebida");
                    System.out.println("D - Eliminar una bebida");

                    char operacion = scanner.nextLine().charAt(0);
                    switch (operacion){
                        case 'A':


                            break;
                        case 'B':


                            break;
                        case 'C':


                            break;
                        case 'D':


                            break;
                    }
                    break;
                case 2:



                    break;
                case 3:



                    break;
                case 4:



                    break;
                case 5:



                    break;
                case 6:


                    break;
                case 7:


                    break;
                case 8:


                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}

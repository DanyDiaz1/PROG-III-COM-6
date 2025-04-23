package view;

import models.entities.Bebida;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import models.enums.TipoBebida;
import services.BebidaService;
import services.ClienteService;
import services.PedidoService;

import java.util.Scanner;

public class MenuController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BebidaService bebidaService = new BebidaService();
    private static final ClienteService clienteService = new ClienteService();
    private static final PedidoService pedidoService = new PedidoService();

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
                            Bebida b = new Bebida();
                            System.out.println("Ingrese el nombre de la bebida");
                            b.setNombre(scanner.nextLine());
                            System.out.println("Ingrese el tipo de Bebida [GASEOSA - JUGO - CERVEZA - AGUA]");
                            b.setTipo(TipoBebida.valueOf(scanner.nextLine().toUpperCase()));
                            System.out.println("Ingrese el precio de la bebida");
                            b.setPrecio(scanner.nextDouble());
                            System.out.println("Ingrese el stock");
                            b.setStock(scanner.nextInt());
                            bebidaService.registrarBebida(b);
                            break;
                        case 'B':
                            bebidaService.listarBebidas().forEach(System.out::println);
                            break;
                        case 'C':
                            System.out.println("Ingrese el id de la bebida que quiere actualizar");
                            int idBebida = scanner.nextInt();
                            scanner.nextLine();
                            if(bebidaService.buscarPorId(idBebida).isPresent()){
                                Bebida bebidaActualizada = new Bebida();
                                System.out.println("Ingrese el nuevo nombre de la bebida");
                                bebidaActualizada.setNombre(scanner.nextLine());
                                System.out.println("Ingrese el nuevo tipo de Bebida [GASEOSA - JUGO - CERVEZA - AGUA]");
                                bebidaActualizada.setTipo(TipoBebida.valueOf(scanner.nextLine().toUpperCase()));
                                System.out.println("Ingrese el nuevo precio de la bebida");
                                bebidaActualizada.setPrecio(scanner.nextDouble());
                                System.out.println("Ingrese el nuevo stock");
                                bebidaActualizada.setStock(scanner.nextInt());
                                bebidaActualizada.setId(idBebida);
                                bebidaService.actualizarBebida(bebidaActualizada);
                            }else{
                                System.out.println("La bebida que quiere actualizar no existe");
                            }
                            break;
                        case 'D':
                            System.out.println("Ingrese el id de la bebida que desea eliminar:");
                            int bebidaAEliminar = scanner.nextInt();
                            bebidaService.eliminarBebida(bebidaAEliminar);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Ingrese el tipo de bebida que quiere ver:");
                    String tipo = scanner.nextLine().toUpperCase();
                    bebidaService.filtrarPorTipo(TipoBebida.valueOf(tipo)).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Ingrese el stock:");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    bebidaService.filtrarPorBajoStock(stock).forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Ingrese la localidad:");
                    String localidad = scanner.nextLine();
                    clienteService.buscarPorLocalidad(localidad).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Ingrese el id del cliente:");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    pedidoService.listarPedidosPorCliente(id).forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Ingrese el id del pedido que quiere cambiar de estado:");
                    int idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedidoService.buscarPorId(idPedido).ifPresent(pedido -> System.out.println("El estado del pedido es: " + pedido.getEstado().name()));
                    System.out.println("Ingrese el estado al que quiere cambiar el pedido:");
                    String nuevoEstado = scanner.nextLine().toUpperCase();
                    pedidoService.cambiarEstadoPedido(idPedido, EstadoPedido.valueOf(nuevoEstado));
                    break;
                case 7:
                    System.out.println("Ingrese el id del cliente que realiza el pedido:");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el id de la bebida que solicita:");
                    int idBebida = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la cantidad de unidades de bebidas:");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();
                    pedidoService.registrarPedido(idCliente,idBebida,cantidad);

                    break;
                case 8:
                    pedidoService.contarPedidosPorEstado().forEach((estado,cantPedidos) -> System.out.println(estado +" - " + cantPedidos));
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}

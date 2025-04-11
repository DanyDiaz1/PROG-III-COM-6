package view;


import model.entities.CredentialEntity;
import model.entities.UsuarioEntity;
import model.entities.enums.Permiso;
import model.exceptions.NoAutorizadoException;
import model.services.CuentaService;
import model.services.UsuarioService;

import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private static UsuarioEntity usuarioAutenticado = null;
    private static final Scanner scanner = new Scanner(System.in);
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final CuentaService cuentaService = new CuentaService();

    public static void run() {
        while (usuarioAutenticado == null) {
            mostrarMenuInicio();
        }
        mostrarMenuPrincipal();
    }

    private static void mostrarMenuInicio() {
        System.out.println("=== Bienvenido al Banco ===");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        switch (opcion) {
            case 1 -> registrarUsuario();
            case 2 -> iniciarSesion();
            case 3 -> {
                System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                System.exit(0);
            }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void registrarUsuario() {
        System.out.println("=== Registro de Usuario ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();


        usuarioService.createUsuario(
                new UsuarioEntity(nombre,apellido,dni,email),
                new CredentialEntity(username, password, Permiso.CLIENTE));

        usuarioAutenticado = usuarioService.findByEmail(email,Permiso.ADMINISTRADOR).orElseThrow();
        System.out.println("Registro exitoso. Sus credenciales han sido generadas.");
    }

    private static void iniciarSesion() {
        System.out.println("=== Iniciar Sesión ===");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Optional<UsuarioEntity> usuario = usuarioService.logUser(username, password);
        usuarioAutenticado = usuario.orElse(null);

        if (usuarioAutenticado == null) {
            System.out.println("Credenciales incorrectas. Intente nuevamente.");
        } else {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuarioAutenticado.getNombre());
        }
    }

    private static void mostrarMenuPrincipal() {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Ver mis cuentas");
            System.out.println("2. Ver todos los usuarios");
            System.out.println("3. Buscar por DNI");
            System.out.println("4. Buscar por Email");
            System.out.println("5. Modificar mis datos");
            System.out.println("6. Modificar datos de otro usuario");
            System.out.println("7. Eliminar usuario");
            System.out.println("8. Ver cuentas propias");
            System.out.println("9. Ver cuentas de otro usuario");
            System.out.println("10. Ver total del saldo propio");
            System.out.println("11. Ver total del saldo de un usuario por DNI");
            System.out.println("12. Depositar en cuenta propia");
            System.out.println("13. Depositar en cuenta de tercero");
            System.out.println("14. Transferir desde cuenta propia");
            System.out.println("15. Transferir entre cuentas terceros");
            System.out.println("16. Ver cantidad de usuarios clientes");
            System.out.println("17. Ver cantidad de cuentas por tipo");
            System.out.println("18. Ver usuario con mayor saldo total");
            System.out.println("19. Listar usuarios por saldo total");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1 -> usuarioAutenticado.getCuentas().forEach(System.out::println);
                case 2 -> {
                    try {
                        usuarioService.findAll(usuarioAutenticado.getCredencial().getPermiso())
                                .forEach(System.out::println);
                    } catch (NoAutorizadoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    System.out.print("DNI: ");
                    String dni = scanner.nextLine();
                    try {
                        System.out.println(usuarioService.findByDNI(dni,usuarioAutenticado.getCredencial().getPermiso()));
                    } catch (NoAutorizadoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    System.out.print("EMAIL: ");
                    String email = scanner.nextLine();
                    try {
                        System.out.println(usuarioService.findByEmail(email,usuarioAutenticado.getCredencial().getPermiso()));
                    } catch (NoAutorizadoException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> modificarMisDatos();
                case 6 -> modificarUsuario();
                case 7 -> {
                    System.out.println("Ingrese el id del usuario a eliminar");
                    Integer id = scanner.nextInt();
                    scanner.nextLine();

                    usuarioService.deleteUser(id,usuarioAutenticado.getCredencial().getPermiso());
                }
                case 8 -> listarCuentasPropias();
                case 9 -> listarCuentas();
                case 10 -> obtenerSaldoTotalPropio();
                case 11 -> obtenerSaldoTotal();
                case 12 -> realizarDepositoPropio();
                case 13 -> realizarDepositoTercero();
                case 14 -> transferirDesdePropia();
                case 15 -> transferir();
                case 16 -> obtenerCantidadPorPermiso();
                case 17 -> obtenerCuentasPorTipo();
                case 18 -> obtenerUsuarioSaldoMayor();
                case 19 -> listarPorSaldoTotal();
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void obtenerUsuarioSaldoMayor(){
        try {
            System.out.println(usuarioService.findByMaxSaldo(usuarioAutenticado.getCredencial().getPermiso()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listarPorSaldoTotal(){
        try {
            usuarioService.listByMaxSaldo(usuarioAutenticado.getCredencial().getPermiso());
        } catch (Exception e) {
            System.out.println("Tipo de usuario no válido.");
        }
    }

    private static void obtenerCuentasPorTipo(){
        cuentaService.viewCuentaCountByTipo(usuarioAutenticado.getCredencial().getPermiso());
    }

    private static void obtenerCantidadPorPermiso(){
        System.out.println("Vera la cantidad de usuarios de tipo Cliente");
        cuentaService.viewCuentaCountByTipo(Permiso.CLIENTE);
    }

    private static void transferirDesdePropia(){
        System.out.println("Ingrese el monto a transferir");
        Double monto = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el id de la cuenta destino");
        Integer idCuentaDestino = scanner.nextInt();
        scanner.nextLine();

        cuentaService.transferir(usuarioAutenticado.getCuentas().getFirst().getId(),
                idCuentaDestino,
                monto,
                usuarioAutenticado);
    }

    public static void transferir(){
        System.out.println("Ingrese el monto a transferir");
        Double monto = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el id de la cuenta origen");
        Integer idCuentaOrigen = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el id de la cuenta destino");
        Integer idCuentaDestino = scanner.nextInt();
        scanner.nextLine();

        try {
            cuentaService.transferir(idCuentaOrigen,
                    idCuentaDestino,
                    monto,
                    usuarioAutenticado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizarDepositoPropio(){
        System.out.println("Ingrese el monto a depositar");
        Double monto = scanner.nextDouble();
        scanner.nextLine();
        cuentaService.depositar(usuarioAutenticado.getCuentas().getFirst().getId(),usuarioAutenticado,monto);
    }

    private static void realizarDepositoTercero(){
        System.out.println("Ingrese el monto a depositar");
        Double monto = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Ingrese el id de la cuenta a depositar");
        Integer idCuenta = scanner.nextInt();
        scanner.nextLine();

        cuentaService.depositar(idCuenta,usuarioAutenticado,monto);
    }

    private static void obtenerSaldoTotalPropio(){
        System.out.println("Su saldo total es de: " + usuarioService.getSaldoTotalPropio(usuarioAutenticado));
    }

    private static void obtenerSaldoTotal(){
        System.out.println("Ingrese el dni del usuario a mostrar el saldo total");
        String dni = scanner.nextLine();
        System.out.println("El saldo total es de : " + usuarioService.getSaldoTotal(dni,Permiso.ADMINISTRADOR));

    }

    private static void listarCuentasPropias(){
        System.out.println(cuentaService.findByUserId(usuarioAutenticado.getId(),usuarioAutenticado));
    }

    private static void listarCuentas(){
        System.out.println("Ingrese el id a mostrar las cuentas");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println(cuentaService.findByUserId(id,usuarioAutenticado));
    }

    private static void modificarMisDatos() {
        System.out.println("=== Modificar Mis Datos ===");

        System.out.print("Nuevo Email: ");
        String email = scanner.nextLine();

        usuarioAutenticado.setEmail(email);
        try {
            usuarioService.updateUser(usuarioAutenticado,usuarioAutenticado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void modificarUsuario(){
        System.out.println("Ingrese el id del usuario a modificar");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        UsuarioEntity aModificar = usuarioService.findUserById(id);

        System.out.print("Nuevo Email: ");
        String email = scanner.nextLine();

        aModificar.setEmail(email);

        try {
            usuarioService.updateUser(aModificar,usuarioAutenticado);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

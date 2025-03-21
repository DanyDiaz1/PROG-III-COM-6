import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    // Listas de datos para usar en los ejercicios
    private static final List<Integer> numeros = List.of(8, 3, 5, 1, 9, 6, 12, 3, 7, 4, 2, 10, 15, 20);
    private static final List<String> nombres = List.of("Juan", "Ana", "Pedro", "Carla", "Miguel");
    private static final List<String> palabras = List.of("Java", "Stream", "Lambda", "Funcional", "API");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;


        do {
            // Menú de opciones
            System.out.println("\n===== MENÚ DE EJERCICIOS STREAMS Y LAMBDAS =====");
            System.out.println("1. Filtrar números pares");
            System.out.println("2. Transformar una lista de nombres a mayúsculas");
            System.out.println("3. Ordenar una lista de números");
            System.out.println("4. Contar elementos mayores a un valor dado");
            System.out.println("5. Obtener los primeros 5 elementos de una lista");
            System.out.println("6. Convertir una lista de palabras en su longitud");
            System.out.println("7. Concatenar nombres con una separación");
            System.out.println("8. Eliminar duplicados de una lista");
            System.out.println("9. Obtener los 3 números más grandes de una lista");
            System.out.println("10. Agrupar palabras por su longitud");
            System.out.println("11. Encontrar el producto de todos los números de una lista");
            System.out.println("12. Obtener el nombre más largo de una lista");
            System.out.println("13. Convertir una lista de enteros en una cadena separada por guiones");
            System.out.println("14. Agrupar una lista de números en pares e impares");
            System.out.println("15. Obtener la suma de los cuadrados de los números impares");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> filtrarNumerosPares();
                case 2 -> transformarNombresAMayusculas();
                case 3 -> ordenarListaNumeros();
                case 4 -> contarMayoresQue(5) ;
                case 5 -> obtenerPrimeros5Elementos();
                case 6 -> convertirPalabrasALongitud();
                case 7 -> concatenarNombres();
                case 8 -> eliminarDuplicados();
                case 9 -> obtenerTop3Numeros();
                case 10 -> agruparPalabrasPorLongitud();
                case 11 -> productoDeNumeros();
                case 12 -> nombreMasLargo();
                case 13 -> listaEnterosComoString();
                case 14 -> agruparParesEImpares();
                case 15 -> sumaDeCuadradosImpares();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida, intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void filtrarNumerosPares() {
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Números pares: " + pares);
    }

    public static void transformarNombresAMayusculas() {
        List<String> mayusculas = nombres.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Nombres en mayúsculas: " + mayusculas);
    }

    public static void ordenarListaNumeros() {
        List<Integer> ordenados = numeros.stream()
                .sorted()
                .toList();
        System.out.println("Números ordenados: " + ordenados);
    }

    public static void contarMayoresQue(int valor) {
        long cantidad = numeros.stream()
                .filter(n -> n > valor)
                .count();
        System.out.println("Cantidad de números mayores que " + valor + ": " + cantidad);
    }

    public static void obtenerPrimeros5Elementos() {
        List<Integer> primeros5 = numeros.stream()
                .limit(5)
                .toList();
        System.out.println("Primeros 5 elementos: " + primeros5);
    }

    public static void convertirPalabrasALongitud() {
        List<Integer> longitudes = palabras.stream()
                .map(String::length)
                .toList();
        System.out.println("Longitudes de palabras: " + longitudes);
    }

    public static void concatenarNombres() {
        String concatenado = nombres.stream()
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        System.out.println("Nombres concatenados: " + concatenado);
    }

    public static void eliminarDuplicados() {
        List<Integer> unicos = numeros.stream()
                .distinct()
                .toList();
        System.out.println("Lista sin duplicados: " + unicos);
    }

    public static void obtenerTop3Numeros() {
        List<Integer> top3 = numeros.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .toList();
        System.out.println("Top 3 números mayores: " + top3);
    }

    public static void agruparPalabrasPorLongitud() {
        Map<Integer, List<String>> agrupadas = palabras.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Palabras agrupadas por longitud: " + agrupadas);
    }

    public static void productoDeNumeros() {
        int producto = numeros.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Producto de todos los números: " + producto);
    }

    public static void nombreMasLargo() {
        String masLargo = nombres.stream()
                .max(Comparator.comparingInt(String::length))
                        .orElse("No existe");
        System.out.println("Nombre más largo: " + masLargo);
    }

    public static void listaEnterosComoString() {
        String resultado = numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("-"));
        System.out.println("Lista como string separado por '-': " + resultado);
    }

    public static void agruparParesEImpares() {
        Map<Boolean, List<Integer>> paresImpares = numeros.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Números pares e impares: " + paresImpares);
    }

    public static void sumaDeCuadradosImpares() {
        int suma = numeros.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("Suma de los cuadrados de los números impares: " + suma);
    }
}

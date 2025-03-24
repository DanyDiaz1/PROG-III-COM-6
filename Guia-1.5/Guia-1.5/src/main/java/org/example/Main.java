package org.example;

import org.example.models.Producto;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final List<Producto> productos = cargarProductos();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Variables
        List<Producto> productoList = null;
        while (true) {
            opciones();
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    productoList = filtrarProductos();
                    System.out.println("Productos encontrados:");
                    productoList.forEach(System.out::println);
                }
                case 2 -> {
                    double promedio = calcularPrecioPromedio();
                    System.out.println(promedio);
                }
                case 3 -> {
                    System.out.println(obtenerProductoMasCaro());
                }
                case 4 -> {
                    String producto = obtenerProductoDeportes()
                            .orElse("Producto Inexistente");
                    System.out.println(producto);
                }
                case 5 -> {
                    System.out.println(obtenerProductoMasBarato());
                }
                case 6 -> {
                    System.out.println(obtenerProductosEnStockOrdenados());
                }
                case 7 -> {
                    System.out.println(calcularStockTotal());

                }
                case 8 -> {
                    Map<String, Integer> categorias = obtenerCantidadProductosPorCategoriaFiltrada();
                    System.out.println(categorias);
                }
                case 9 -> {
                    productoList = aplicarDescuento();
                    System.out.println("Productos con descuento:");
                    productoList.forEach(System.out::println);
                }
                case 10 -> {
                    System.out.println(calcularGananciaTotal());
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }


    }
    public static void opciones(){
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Filtrado y Transformación");
        System.out.println("2. Reducción de Datos");
        System.out.println("3. Producto más caro por categoría");
        System.out.println("4. Uso de Optionals");
        System.out.println("5. Producto más barato en stock total");
        System.out.println("6. Productos en stock ordenados alfabéticamente");
        System.out.println("7. Cálculo de stock total considerando precio superior al promedio");
        System.out.println("8. Stock por categoría excluyendo categorías con menos de 3 productos");
        System.out.println("9. Aplicar descuento a productos con más de 20 unidades en stock");
        System.out.println("10. Ganancia total del inventario");
        System.out.println("0. Salir");
        System.out.println("Seleccione un ejercicio (1-10) o 0 para salir:");
    }
    //--- EJERCICIOS ---
    /*1. Filtrado y Transformación
      ○  Obtener una lista con los nombres y precios de los productos de la categoría
        "Electrónica" cuyo precio sea mayor a 1000, ordenados de mayor a menor precio.*/
    public static List<Producto> filtrarProductos() {
        return productos.stream()
                .filter(n -> n.getCategoria().equals("Electrónica") && n.getPrecio() > 1000)
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .toList();
    }

    /*2. Reducción de Datos
        ○ Calcular el precio promedio de los productos de la categoría "Hogar", pero
        solo considerando aquellos con stock disponible.*/
    public static double calcularPrecioPromedio() {
        return productos.stream()
                .filter(n -> n.getCategoria().equals("Hogar") && n.getStock() > 0)
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0);
    }

    /*3. Producto mas caro
     ○ Obtener el producto más caro de cada categoría y devolver un mapa con la
    categoría como clave y el producto más caro como valor.
        */
    public static Map<String, Producto> obtenerProductoMasCaro() {
        return productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Producto::getPrecio)),
                                Optional::get)));

        /*return productos.stream()
                .collect(
                        Collectors
                                .toMap(Producto::getCategoria,
                                        Function.identity(),
                                        BinaryOperator
                                        .maxBy(Comparator.comparingDouble(Producto::getPrecio))));*/

//        return productos.stream()
//                .collect(Collectors.toMap(
//                        Producto::getCategoria, //Clave
//                        producto -> producto, //Valor
//                        (producto1, producto2) ->
//                                producto1.getPrecio() > producto2.getPrecio() ? producto1 : producto2
//                )); //Filtrado para cuando hay mas de un valor por clave posible.
    }
    /*4. Uso de Optionals
    ○ Encontrar el producto de la categoría "Deportes" con stock mayor a 10
    unidades, obtener su nombre en minúsculas y devolverlo dentro de un
    Optional. Mostrarlo o si no existe, mostrar “Producto Inexistente”*/

    public static Optional<String> obtenerProductoDeportes() {
        return productos.stream()
                .filter(n -> n.getCategoria().equals("Deportes") && n.getStock() > 10)
                .map(n -> n.getNombre().toLowerCase())
                .findFirst();
    }

    /* 5. Producto Mas Barato
    ○ Encontrar el producto mas barato calculando el valor total de todas las
    unidades en stock (Precio * stock). Devolver un Opcional con el producto. En
    caso de que no exista, lanzar una excepción.
     */
    public static Producto obtenerProductoMasBarato() {
        return productos.stream()
                .min(Comparator.comparingDouble(p -> p.getPrecio() * p.getStock()))
                .orElseThrow(() -> new NoSuchElementException("No existe ningún producto."));

//        return productos.stream()
//                .reduce((p1, p2) -> p1.getPrecio() * p1.getStock()
//                        < p2.getPrecio() * p2.getStock() ? p1 : p2)
//                .orElseThrow(NoSuchElementException::new);
    }

    /*6. Productos en stock ordenados alfabéticamente
    ○ Obtener una lista con los nombres de los productos que tienen stock,
    ordenarlos alfabéticamente y excluir los productos con nombres de menos de
    5 caracteres.
*/
    public static List<String> obtenerProductosEnStockOrdenados() {
        return productos.stream()
                .filter(n -> n.getNombre().length() >= 5 && n.getStock() > 0)
                .sorted(Comparator.comparing(Producto::getNombre))
                .map(Producto::getNombre)
                .toList();
    }

    /*7. Calculo de Stock Total
    ○ Obtener el total de unidades en stock de todos los productos, pero solo
    considerando aquellos con precio superior al promedio.*/
    public static int calcularStockTotal() {
        double precioPromedio = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .orElse(0);

        return productos.stream()
                .filter(n -> n.getPrecio() > precioPromedio)
                .mapToInt(Producto::getStock)
                .sum();
    }

    /*8. Stock por Categoría
    ○ Calcular cuántas unidades en stock hay por categoría, excluyendo categorías
    con menos de 3 productos*/
    public static Map<String, Integer> obtenerCantidadProductosPorCategoriaFiltrada() {
        Map<String, Long> cantidadPorCategoria = productos.stream()
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.counting()));

        return productos.stream()
                .filter(producto -> cantidadPorCategoria.get(producto.getCategoria()) >= 3)
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.summingInt(Producto::getStock)));
    }

    /*9. Aplicar descuento
    ○ Crear una nueva lista de productos con un 15% de descuento en su precio,
    pero solo si el producto tiene más de 20 unidades en stock.
    */
    public static List<Producto> aplicarDescuento() {
        return productos.stream()
                .filter(n -> n.getStock() > 20)
                .map(n -> new Producto(n.getNombre(), n.getPrecio() * 0.85, n.getCategoria(), n.getStock()))
                .toList();
    }

    /*10. Ganancia total inventario
    ○ Calcular la ganancia total si se vendiera todo el inventario, considerando que
    el costo de compra de cada producto es un 45% del valor de venta o de un
    65% si pertenece a la categoria Electronica.
    */
    public static double calcularGananciaTotal() {
        return productos.stream()
                .mapToDouble(p -> {
                    double costo = p.getCategoria().equals("Electrónica") ? p.getPrecio() * 0.65 : p.getPrecio() * 0.45;
                    return (p.getPrecio() - costo) * p.getStock(); //calculo de la ganancia
                })
                .sum();
    }

    public static List<Producto> cargarProductos() {
        return List.of(
                new Producto("Laptop", 1500, "Electrónica", 5),
                new Producto("Smartphone", 800, "Electrónica", 10),
                new Producto("Televisor", 1200, "Electrónica", 3),
                new Producto("Heladera", 2000, "Hogar", 2),
                new Producto("Microondas", 500, "Hogar", 8),
                new Producto("Silla", 150, "Muebles", 12),
                new Producto("Mesa", 300, "Muebles", 7),
                new Producto("Zapatillas", 100, "Deportes", 15),
                new Producto("Pelota", 50, "Deportes", 20),
                new Producto("Bicicleta", 500, "Deportes", 5),
                new Producto("Libro", 30, "Librería", 50),
                new Producto("Cuaderno", 10, "Librería", 100),
                new Producto("Lámpara", 80, "Hogar", 30),
                new Producto("Cafetera", 250, "Hogar", 6),
                new Producto("Auriculares", 120, "Electrónica", 14),
                new Producto("Teclado", 90, "Electrónica", 9),
                new Producto("Mouse", 60, "Electrónica", 18),
                new Producto("Monitor", 700, "Electrónica", 4),
                new Producto("Cama", 800, "Muebles", 2),
                new Producto("Sofá", 1000, "Muebles", 3),
                new Producto("Espejo", 120, "Hogar", 12),
                new Producto("Ventilador", 150, "Hogar", 7),
                new Producto("Patines", 180, "Deportes", 5),
                new Producto("Raqueta", 220, "Deportes", 6),
                new Producto("Taza", 15, "Hogar", 40)
        );
    }


}
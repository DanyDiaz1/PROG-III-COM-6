package streams.parallelStream;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = Arrays.asList(
                new Producto("Laptop", 1000),
                new Producto("Mouse", 50),
                new Producto("Teclado", 80),
                new Producto("Monitor", 300)
        );

        productos.parallelStream()
                .map(Producto::getPrecioConImpuesto)
                .forEach(System.out::println);

        //preciosConImpuestos.forEach(System.out::println);
    }
}

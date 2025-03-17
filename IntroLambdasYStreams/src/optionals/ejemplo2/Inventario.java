package optionals.ejemplo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Inventario {
    private static final Map<String, Producto> productos = new HashMap<>();

    static {
        productos.put("123", new Producto("Laptop"));
        productos.put("456", new Producto("Teléfono"));
    }

    public static Optional<Producto> buscarProducto(String codigo) {
        return Optional.ofNullable(productos.get(codigo));
    }
}

package optionals.ejemplo2;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        String codigo = "999";  // Producto inexistente
        Producto producto = Inventario.buscarProducto(codigo)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado"));

        System.out.println("Producto: " + producto.getNombre());
    }
}

package optionals.ejemplo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tienda {
    private final List<Producto> productos = new ArrayList<>();

    // Agregar producto a la tienda
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Buscar producto por nombre
    public Optional<Producto> buscarPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    // Buscar productos por categoría
    public List<Producto> buscarPorCategoria(String categoria) {
        return productos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    // Obtener precio de un producto con valor por defecto
    public double obtenerPrecioProducto(String nombre) {
        return buscarPorNombre(nombre)
                .map(Producto::getPrecio)
                .orElse(99.99); // Precio por defecto si el producto no existe
    }

    // Eliminar producto si existe
    public void eliminarProducto(String nombre) {
        buscarPorNombre(nombre).ifPresent(producto -> {
            productos.remove(producto);
            System.out.println("Producto eliminado: " + producto.getNombre());
        });
    }

    // Mostrar todos los productos
    public void listarProductos() {
        productos.forEach(System.out::println);
    }
}

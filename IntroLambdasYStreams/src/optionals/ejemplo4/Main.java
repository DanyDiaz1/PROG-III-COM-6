package optionals.ejemplo4;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda();

        // Agregar productos
        tienda.agregarProducto(new Producto("Laptop", "Electrónica", 1200.0));
        tienda.agregarProducto(new Producto("Celular", "Electrónica", 800.0));
        tienda.agregarProducto(new Producto("Silla Gamer", "Muebles", 300.0));

        // Buscar un producto por nombre
        tienda.buscarPorNombre("Laptop").ifPresent(System.out::println);

        // Obtener el precio de un producto
        System.out.println("Precio del Celular: $" + tienda.obtenerPrecioProducto("Celular"));
        System.out.println("Precio de un producto inexistente: $" + tienda.obtenerPrecioProducto("TV"));

        // Buscar productos por categoría
        System.out.println("\nProductos en la categoría 'Electrónica':");
        tienda.buscarPorCategoria("Electrónica").forEach(System.out::println);

        // Intentar eliminar un producto existente y uno inexistente
        tienda.eliminarProducto("Silla Gamer");
        tienda.eliminarProducto("Tablet"); // No hará nada porque no existe

        // Listar los productos restantes
        System.out.println("\nProductos disponibles:");
        tienda.listarProductos();
    }
}

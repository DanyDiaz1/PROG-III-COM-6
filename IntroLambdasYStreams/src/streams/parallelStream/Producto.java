package streams.parallelStream;

public class Producto {
    String nombre;
    double precio;

    Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public double getPrecioConImpuesto() {
        return precio * 1.21; // 21% de IVA
    }
}

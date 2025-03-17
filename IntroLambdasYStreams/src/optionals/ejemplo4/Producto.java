package optionals.ejemplo4;

public class Producto {
    private String nombre;
    private String categoria;
    private double precio;

    public Producto(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", categoría='" + categoria + '\'' +
                ", precio=" + precio + '}';
    }
}

package org.example.models;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private int stock;
    // Constructor, Getters y Setters
    public Producto(String nombre, double precio, String categoria, int
            stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto\n{" +
                "nombre='" + nombre + '\'' +
                ",\n precio=" + precio +
                ",\n categoria='" + categoria + '\'' +
                ",\n stock=" + stock +
                '}';
    }
}

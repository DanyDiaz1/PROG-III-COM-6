package models.entities;

import models.enums.TipoBebida;

public class Bebida {
    private int id;
    private String nombre;
    private TipoBebida tipo;
    private double precio;
    private int stock;

    public Bebida(int id, String nombre, TipoBebida tipo, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.stock = stock;
    }

    public Bebida(String nombre, TipoBebida tipo, double precio, int stock) {
        this(0, nombre, tipo, precio, stock);
    }

    public Bebida() {

    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoBebida getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoBebida tipo) {
        this.tipo = tipo;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}

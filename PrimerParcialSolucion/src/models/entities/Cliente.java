package models.entities;

import models.enums.TipoCliente;

public class Cliente {
    private int id;
    private String nombre;
    private String localidad;
    private TipoCliente tipo;

    public Cliente(int id, String nombre, String localidad, TipoCliente tipo) {
        this.id = id;
        this.nombre = nombre;
        this.localidad = localidad;
        this.tipo = tipo;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", localidad='" + localidad + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}

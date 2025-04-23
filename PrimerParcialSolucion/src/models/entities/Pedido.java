package models.entities;

import models.enums.EstadoPedido;

public class Pedido {
    private int id;
    private int idCliente;
    private int idBebida;
    private int cantidad;
    private EstadoPedido estado;

    public Pedido(int id, int idCliente, int idBebida, int cantidad, EstadoPedido estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idBebida = idBebida;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public Pedido() {

    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdBebida() {
        return idBebida;
    }

    public void setIdBebida(int idBebida) {
        this.idBebida = idBebida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idBebida=" + idBebida +
                ", cantidad=" + cantidad +
                ", estado=" + estado +
                '}';
    }
}

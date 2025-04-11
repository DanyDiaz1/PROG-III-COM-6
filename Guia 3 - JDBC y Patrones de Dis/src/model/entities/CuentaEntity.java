package model.entities;



import model.entities.enums.TipoCuenta;

import java.time.LocalDateTime;

public class CuentaEntity {
    private Integer id;
    private Integer usuario_id;
    private TipoCuenta tipo;
    private Double saldo;
    private LocalDateTime fechaCreacion;

    public CuentaEntity(Integer id, Integer usuario_id, TipoCuenta tipo, Double saldo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.tipo = tipo;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public CuentaEntity(Integer usuario_id, TipoCuenta tipo, Double saldo) {
        this.usuario_id = usuario_id;
        this.tipo = tipo;
        this.saldo = saldo;
    }

    public CuentaEntity(TipoCuenta tipo, Double saldo, LocalDateTime fechaCreacion) {
        this.tipo = tipo;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public CuentaEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public String toString() {
        return "CuentaEntity{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", saldo=" + saldo +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}

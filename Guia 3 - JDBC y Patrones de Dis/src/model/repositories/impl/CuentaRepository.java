package model.repositories.impl;


import config.DBConnection;
import model.entities.CuentaEntity;
import model.entities.enums.TipoCuenta;
import model.repositories.interfaces.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CuentaRepository implements IRepository<CuentaEntity> {

    private final Connection conn;
    public CuentaRepository() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public void save(CuentaEntity cuentaEntity) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO cuentas (id_cuenta,id_usuario,tipo, saldo) VALUES (?, ? ,?, ?)")) {

            ps.setInt(1, cuentaEntity.getId());
            ps.setInt(2, cuentaEntity.getUsuario_id());
            ps.setString(3, cuentaEntity.getTipo().toString());
            ps.setDouble(4, cuentaEntity.getSaldo());
            ps.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CuentaEntity> findAll() throws SQLException {
        List<CuentaEntity> cuentas = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM cuentas");
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                cuentas.add(new CuentaEntity(
                        rs.getInt("id_cuenta"),
                        rs.getInt("id_usuario"),
                        TipoCuenta.valueOf(rs.getString("tipo")),
                        rs.getDouble("saldo"),
                        rs.getTimestamp("fecha_creacion").toLocalDateTime()
                ));
            }
        }
        return cuentas;
    }

    @Override
    public Optional<CuentaEntity> findById(Integer id) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM cuentas WHERE id_cuenta = ?");){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new CuentaEntity(
                            rs.getInt("id_cuenta"),
                            rs.getInt("id_usuario"),
                            TipoCuenta.valueOf(rs.getString("tipo")),
                            rs.getDouble("saldo"),
                            rs.getTimestamp("fecha_creacion").toLocalDateTime()
                    ));
                }
                return Optional.empty();
            }
        }
    }

    @Override
    public void update(CuentaEntity cuentaEntity) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE cuentas SET saldo = ? WHERE id_cuenta = ?")) {

            ps.setDouble(1, cuentaEntity.getSaldo());
            ps.setInt(2,cuentaEntity.getId());
            ps.execute();
        }
    }

    @Override
    public void delete(CuentaEntity cuentaEntity) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("DELETE from cuentas where id_cuenta = ?")) {
            ps.setInt(1, cuentaEntity.getId());
            ps.execute();
        }
    }

    public List<CuentaEntity> findAllByUserId(Integer id_usuario) throws SQLException {
        List<CuentaEntity> cuentas = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM cuentas WHERE id_usuario = ?");){

            ps.setInt(1, id_usuario);
            try(ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    cuentas.add(new CuentaEntity(
                            rs.getInt("id_cuenta"),
                            rs.getInt("id_usuario"),
                            TipoCuenta.valueOf(rs.getString("tipo")),
                            rs.getDouble("saldo"),
                            rs.getTimestamp("fecha_creacion").toLocalDateTime()
                    ));
                }
            }
        }

        return cuentas;
    }
}

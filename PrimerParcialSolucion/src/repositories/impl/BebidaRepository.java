package repositories.impl;

import config.DBConnection;
import models.entities.Bebida;
import models.enums.TipoBebida;
import repositories.interfaces.IABMRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BebidaRepository implements IABMRepository<Bebida> {

    private final Connection conn;

    public BebidaRepository() {
        this.conn = DBConnection.getInstance().getConnection();
    }

    public void create(Bebida bebida) throws SQLException {
        String sql = "INSERT INTO bebida (nombre, tipo, precioUnitario, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bebida.getNombre());
            ps.setString(2, bebida.getTipo().name());
            ps.setDouble(3, bebida.getPrecio());
            ps.setInt(4, bebida.getStock());
            ps.executeUpdate();
        }
    }

    public void update(Bebida bebida) throws SQLException {
        String sql = "UPDATE bebida SET nombre = ?, tipo = ?, precioUnitario = ?, stock = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bebida.getNombre());
            ps.setString(2, bebida.getTipo().name());
            ps.setDouble(3, bebida.getPrecio());
            ps.setInt(4, bebida.getStock());
            ps.setInt(5, bebida.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Bebida findById(int id) throws SQLException {
        String sql = "SELECT * FROM bebida WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Bebida(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        TipoBebida.valueOf(rs.getString("tipo")),
                        rs.getDouble("precioUnitario"),
                        rs.getInt("stock")
                );
            }
        }
        return null;
    }

    public List<Bebida> findAll() throws SQLException {
        List<Bebida> lista = new ArrayList<>();
        String sql = "SELECT * FROM bebida";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Bebida bebida = new Bebida(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        TipoBebida.valueOf(rs.getString("tipo")),
                        rs.getDouble("precioUnitario"),
                        rs.getInt("stock")
                );
                lista.add(bebida);
            }
        }
        return lista;
    }
}

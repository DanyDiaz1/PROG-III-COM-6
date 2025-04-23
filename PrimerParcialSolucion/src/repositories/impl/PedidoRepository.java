package repositories.impl;

import config.DBConnection;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import repositories.interfaces.IABMRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepository implements IABMRepository<Pedido> {
    private final Connection conn;

    public PedidoRepository(){
        this.conn = DBConnection.getInstance().getConnection();
    }

    @Override
    public List<Pedido> findAll() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();

        String query = "SELECT * FROM Pedido";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idBebida"),
                        rs.getInt("cantidad"),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );
                pedidos.add(pedido);
            }
        }

        return pedidos;
    }

    @Override
    public Pedido findById(int id) throws SQLException {
        String query = "SELECT * FROM Pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Pedido(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getInt("idBebida"),
                        rs.getInt("cantidad"),
                        EstadoPedido.valueOf(rs.getString("estado"))
                );
            }
        }
        return null;
    }

    @Override
    public void update(Pedido pedido) throws SQLException {
        String query = "UPDATE Pedido SET estado = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, pedido.getEstado().name());
            ps.setInt(2, pedido.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void create(Pedido pedido)throws SQLException {
        String sql = "INSERT INTO Pedido (idCliente, idBebida, cantidad, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setInt(2, pedido.getIdBebida());
            stmt.setInt(3, pedido.getCantidad());
            stmt.setString(4, pedido.getEstado().name());

            stmt.executeUpdate();
        }
    }
}

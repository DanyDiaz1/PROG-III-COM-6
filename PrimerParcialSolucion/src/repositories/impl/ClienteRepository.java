package repositories.impl;

import config.DBConnection;
import models.entities.Cliente;
import models.enums.TipoCliente;
import repositories.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements IRepository<Cliente> {

    private final Connection conn;

    public ClienteRepository(){
        this.conn = DBConnection.getInstance().getConnection();
    }

    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();

        String query = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("localidad"),
                        TipoCliente.valueOf(rs.getString("tipoCliente"))
                );
                clientes.add(cliente);
            }
        }

        return clientes;
    }

    public Cliente findById(int id) throws SQLException {
        String query = "SELECT * FROM cliente WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("localidad"),
                        TipoCliente.valueOf(rs.getString("tipoCliente"))
                );
            }
        }

        return null;
    }
}

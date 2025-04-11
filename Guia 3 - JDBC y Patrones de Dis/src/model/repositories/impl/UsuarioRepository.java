package model.repositories.impl;


import config.DBConnection;
import model.entities.UsuarioEntity;
import model.repositories.interfaces.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepository implements IRepository<UsuarioEntity> {

    private final Connection conn;

    public UsuarioRepository() {
        this.conn = DBConnection.getInstance().getConnection();
    }



    @Override
    public void save(UsuarioEntity usuarioEntity) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement
                ("INSERT INTO usuarios (id_usuario, nombre, apellido, dni, email) VALUES (?,?,?,?,?)")) {

            ps.setLong(1, usuarioEntity.getId());
            ps.setString(2, usuarioEntity.getNombre());
            ps.setString(3, usuarioEntity.getApellido());
            ps.setString(4, usuarioEntity.getDni());
            ps.setString(5, usuarioEntity.getEmail());
            ps.execute();
        }
    }

    @Override
    public List<UsuarioEntity> findAll() throws SQLException {
        List<UsuarioEntity> usuarios = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from usuarios");
            ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    usuarios.add(new UsuarioEntity(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("email"),
                            rs.getTimestamp("fecha_creacion").toLocalDateTime()));
                }

        }

        return usuarios;
    }

    @Override
    public Optional<UsuarioEntity> findById(Integer id) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("select * from usuarios where id_usuario = ?")) {

            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new UsuarioEntity(
                            rs.getInt("id_usuario"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("dni"),
                            rs.getString("email"),
                            rs.getTimestamp("fecha_creacion").toLocalDateTime()));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(UsuarioEntity usuarioEntity) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement
                ("UPDATE usuarios SET nombre = ?, apellido = ?, dni = ?, email = ? WHERE id_usuario = ?")) {
            ps.setString(1, usuarioEntity.getNombre());
            ps.setString(2, usuarioEntity.getApellido());
            ps.setString(3, usuarioEntity.getDni());
            ps.setString(4, usuarioEntity.getEmail());
            ps.setLong(5, usuarioEntity.getId());
            ps.execute();
        }
    }

    @Override
    public void delete(UsuarioEntity usuarioEntity) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id_usuario = ?")) {
            ps.setLong(1, usuarioEntity.getId());
            ps.execute();
        }
    }

    public Integer findLastId(){
        try(PreparedStatement ps = conn.prepareStatement("SELECT max(id_usuario) as id FROM usuarios")) {
            try(ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

package model.repositories.impl;



import config.DBConnection;
import model.entities.CredentialEntity;
import model.entities.enums.Permiso;
import model.repositories.interfaces.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CredentialRepository implements IRepository<CredentialEntity> {

    private final Connection conn;

    public CredentialRepository() {
        this.conn = DBConnection.getInstance().getConnection();
    }



    @Override
    public void save(CredentialEntity credentialEntity) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO credenciales (id_usuario,username, password, permiso) VALUES (?,?,?,?)")) {

            ps.setInt(1, credentialEntity.getId_usuario());
            ps.setString(2, credentialEntity.getUsername());
            ps.setString(3, credentialEntity.getPassword());
            ps.setString(4, credentialEntity.getPermiso().toString());
            ps.execute();

        }
    }

    @Override
    public List<CredentialEntity> findAll() throws SQLException {
        List<CredentialEntity> credentials = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement("select * from credenciales");
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                credentials.add(new CredentialEntity(
                        rs.getInt("id_credencial"),
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Permiso.valueOf(rs.getString("permiso"))));
            }
        }
        return credentials;
    }

    @Override
    public Optional<CredentialEntity> findById(Integer id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("select * from credenciales where id_credencial = ?")) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new CredentialEntity(
                            rs.getInt("id_credencial"),
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            Permiso.valueOf(rs.getString("permiso"))));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<CredentialEntity> findByUserId(Integer id_usuario) throws SQLException{
        try(PreparedStatement ps = conn.prepareStatement("SELECT * from credenciales where id_usuario = ?")){
            ps.setInt(1,id_usuario);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return Optional.of(new CredentialEntity(
                            rs.getInt("id_credencial"),
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            Permiso.valueOf(rs.getString("permiso"))
                    ));
                }
            }
        }
        return Optional.empty();
    }

    public Optional<CredentialEntity> findByUsername(String username) throws SQLException{
        try(PreparedStatement ps = conn.prepareStatement("SELECT * from credenciales where username = ?")){
            ps.setString(1,username);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return Optional.of(new CredentialEntity(
                            rs.getInt("id_credencial"),
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            Permiso.valueOf(rs.getString("permiso"))
                    ));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(CredentialEntity credentialEntity) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(
                "UPDATE credenciales SET username = ?, password = ?, permiso = ? WHERE id_credencial = ?")) {

            ps.setString(1, credentialEntity.getUsername());
            ps.setString(2, credentialEntity.getPassword());
            ps.setString(3, credentialEntity.getPermiso().toString());
            ps.setLong(4, credentialEntity.getId());
            ps.execute();
        }
    }

    @Override
    public void delete(CredentialEntity credentialEntity) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                     "DELETE  FROM credenciales WHERE id_credencial = ?")) {

            ps.setInt(1, credentialEntity.getId());

            ps.execute();
        }
    }
}

package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConexionMySQL;

public class DireccionDAO {
    private Connection conexion;

    public DireccionDAO() {
        this.conexion = ConexionMySQL.getInstancia().getConexion();
    }

    public void insertarDireccion(Direccion direccion) {
        String sql = "INSERT INTO direcciones (calle, altura, alumno_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, direccion.getCalle());
            stmt.setInt(2, direccion.getAltura());
            stmt.setInt(3, direccion.getAlumnoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Direccion> obtenerDireccionesPorAlumno(int alumnoId) {
        List<Direccion> direcciones = new ArrayList<>();
        String sql = "SELECT * FROM direcciones WHERE alumno_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, alumnoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Direccion direccion = new Direccion();
                direccion.setId(rs.getInt("id"));
                direccion.setCalle(rs.getString("calle"));
                direccion.setAltura(rs.getInt("altura"));
                direccion.setAlumnoId(rs.getInt("alumno_id"));
                direcciones.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return direcciones;
    }

    public void eliminarDireccion(int id) {
        String sql = "DELETE FROM direcciones WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

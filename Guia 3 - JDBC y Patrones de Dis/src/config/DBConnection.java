package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // URL de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "1234";

    // Instancia única
    private static DBConnection instance;
    private Connection connection;

    // Constructor privado para inicializar la conexión
    private DBConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    // Método público para obtener la instancia única
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;  // Resetear la conexión
                instance = null;  // Resetear la instancia
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                throw new RuntimeException("Error al cerrar la conexión", e);
            }
        }
    }
}

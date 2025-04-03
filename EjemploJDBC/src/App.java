import config.DBConnection;
import controller.EstudianteController;
import dao.EstudianteDAO;
import view.EstudianteVista;

public class App {
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        EstudianteVista estudianteVista = new EstudianteVista();
        EstudianteController controlador = new EstudianteController(estudianteDAO, estudianteVista);

        // Pruebas CRUD
        //controlador.agregarEstudiante("Juan Pérez", 20);
        //controlador.listarEstudiantes();
        controlador.actualizarEstudiante(1, "Edu", 26);
        controlador.listarEstudiantes();
        //controlador.eliminarEstudiante(1);
        //controlador.listarEstudiantes();

        DBConnection.getInstance().cerrarConexion();
    }
}

package controller;

import model.Direccion;
import model.DireccionDAO;

import java.util.List;

public class DireccionController {
    private DireccionDAO direccionDAO;

    public DireccionController() {
        this.direccionDAO = new DireccionDAO();
    }

    public void agregarDireccion(String calle, int altura, int alumnoId) {
        Direccion direccion = new Direccion(0, calle, altura, alumnoId);
        direccionDAO.insertarDireccion(direccion);
    }

    public List<Direccion> obtenerDireccionesPorAlumno(int alumnoId) {
        return direccionDAO.obtenerDireccionesPorAlumno(alumnoId);
    }

    public void eliminarDireccion(int id) {
        direccionDAO.eliminarDireccion(id);
    }
}

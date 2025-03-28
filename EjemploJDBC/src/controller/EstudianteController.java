package controller;

import dao.EstudianteDAO;
import model.Estudiante;
import view.EstudianteVista;

import java.util.List;

public class EstudianteController {
    private final EstudianteDAO estudianteDAO;
    private final EstudianteVista  estudianteVista;

    public EstudianteController(EstudianteDAO estudianteDAO, EstudianteVista estudianteVista) {
        this.estudianteDAO = estudianteDAO;
        this.estudianteVista = estudianteVista;
    }

    public void listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteDAO.obtenerEstudiantes();
        estudianteVista.mostrarEstudiantes(estudiantes);
    }

    public void agregarEstudiante(String nombre, int edad) {
        estudianteDAO.agregarEstudiante(new Estudiante(0, nombre, edad));
    }

    public void actualizarEstudiante(int id, String nombre, int edad) {
        estudianteDAO.actualizarEstudiante(new Estudiante(id, nombre, edad));
    }

    public void eliminarEstudiante(int id) {
        estudianteDAO.eliminarEstudiante(id);
    }
}

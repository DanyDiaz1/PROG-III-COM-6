package controller;

import model.Alumno;
import model.AlumnoDAO;

import java.util.List;

public class AlumnoController {
    private AlumnoDAO alumnoDAO;

    public AlumnoController() {
        this.alumnoDAO = new AlumnoDAO();
    }

    public void agregarAlumno(String nombre, String apellido, int edad, String email) {
        Alumno alumno = new Alumno(0, nombre, apellido, edad, email);
        alumnoDAO.insertarAlumno(alumno);
    }

    public List<Alumno> obtenerAlumnos() {
        return alumnoDAO.obtenerTodosLosAlumnos();
    }

    public void actualizarEdad(int id, int nuevaEdad) {
        alumnoDAO.actualizarEdad(id, nuevaEdad);
    }

    public void eliminarAlumno(int id) {
        alumnoDAO.eliminarAlumno(id);
    }
}

package view;

import model.Estudiante;

import java.util.List;

public class EstudianteVista {
    public void mostrarEstudiantes(List<Estudiante> estudiantes) {
        for (Estudiante estudiante : estudiantes) {
            System.out.println("ID: " + estudiante.getId() + ", Nombre: " + estudiante.getNombre() + ", Edad: " + estudiante.getEdad());
        }
    }
}

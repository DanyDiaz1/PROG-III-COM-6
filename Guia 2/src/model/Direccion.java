package model;

public class Direccion {
    private int id;
    private String calle;
    private int altura;
    private int alumnoId;


    public Direccion() {

    }

    public Direccion(int id, String calle, int altura, int alumnoId) {
        this.id = id;
        this.calle = calle;
        this.altura = altura;
        this.alumnoId = alumnoId;
    }

    public Direccion(String calle, int altura, int alumnoId) {
        this(0, calle, altura, alumnoId);
    }

    public int getId() {
        return id;
    }

    public String getCalle() {
        return calle;
    }

    public int getAltura() {
        return altura;
    }

    public int getAlumnoId() {
        return alumnoId;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setAlumnoId(int alumnoId) {
        this.alumnoId = alumnoId;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Calle: " + calle + " " + altura + " (Alumno ID: " + alumnoId + ")";
    }
}

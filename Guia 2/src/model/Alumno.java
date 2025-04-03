package model;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String email;

    public Alumno() {

    }
    public Alumno(int id, String nombre, String apellido, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
    }

    public Alumno(String nombre, String apellido, int edad, String email) {
        this(0, nombre, apellido, edad, email);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + nombre + " " + apellido + " - Edad: " + edad + " - Email: " + email;
    }
}

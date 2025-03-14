package modelo;

import java.util.Date;

public class Expansion extends Media{
    private final Date fechaLanzamiento;

    public Expansion(String titulo, String creador, String genero, String identificador, Date fechaLanzamiento) {
        super(titulo, creador, genero, identificador);
        if (fechaLanzamiento == null) throw new IllegalArgumentException("La fecha de lanzamiento no puede ser nula.");
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Date getFechaLanzamiento() { return fechaLanzamiento; }

    @Override
    public String toString() {
        return super.toString() + " - Lanzamiento: " + fechaLanzamiento;
    }
}

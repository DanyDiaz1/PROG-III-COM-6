package modelo;

public class Videojuego extends Media{
    private int version;

    public Videojuego(String titulo, String creador, String genero, String identificador, int version) {
        super(titulo, creador, genero, identificador);
        if (version <= 0) throw new IllegalArgumentException("La versión debe ser positiva.");
        this.version = version;
    }

    public int getVersion() { return version; }

    @Override
    public String toString() {
        return super.toString() + " - Versión: " + version;
    }
}

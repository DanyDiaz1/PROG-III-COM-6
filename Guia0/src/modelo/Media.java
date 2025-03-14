package modelo;

public abstract class Media implements IMedia, Comparable<Media> {
    protected String titulo;
    protected String creador;
    protected String genero;
    protected String identificador;

    public Media(String titulo, String creador, String genero, String identificador) {
        this.titulo = titulo;
        this.creador = creador;
        this.genero = genero;
        this.identificador = identificador;
    }

    @Override
    public String getTitulo() { return titulo; }
    @Override
    public String getCreador() { return creador; }
    @Override
    public String getGenero() { return genero; }
    @Override
    public String getIdentificador() { return identificador; }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int compareTo(Media otra) {
        return this.titulo.compareTo(otra.getIdentificador());
    }

    @Override
    public String toString() {
        return titulo + " - " + creador + " (" + genero + ")";
    }
}

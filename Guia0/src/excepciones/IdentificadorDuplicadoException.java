package excepciones;

public class IdentificadorDuplicadoException extends RuntimeException{
    public IdentificadorDuplicadoException(String mensaje) {
        super(mensaje);
    }
}

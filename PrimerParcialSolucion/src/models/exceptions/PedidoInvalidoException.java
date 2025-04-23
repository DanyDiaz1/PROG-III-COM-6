package models.exceptions;

public class PedidoInvalidoException extends RuntimeException {
    public PedidoInvalidoException(String message) {
        super(message);
    }
}

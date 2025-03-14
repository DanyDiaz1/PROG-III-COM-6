package repositorios;

import excepciones.IdentificadorDuplicadoException;
import modelo.IMedia;

import java.util.List;

public interface IRepositorio <T extends IMedia> {
    void agregar(T item) throws IdentificadorDuplicadoException;
    void eliminar(String identificador);
    T buscarPorGenero(String identificador);
    List<T> obtenerTodos();
    boolean actualizarAtributo(String identificador, String atributo, String nuevoValor);
}

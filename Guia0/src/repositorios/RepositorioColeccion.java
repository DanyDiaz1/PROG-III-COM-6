package repositorios;

import excepciones.IdentificadorDuplicadoException;
import modelo.Media;

import java.util.*;

public class RepositorioColeccion<T extends Media> implements IRepositorio<T> {
    private final Set<T> coleccion = new HashSet<>();

    public void agregar(T elemento) throws IdentificadorDuplicadoException {
        for (T media : coleccion) {
            if (media.getIdentificador().equals(elemento.getIdentificador())) {
                throw new IdentificadorDuplicadoException("El identificador ya existe en la colección.");
            }
        }
        coleccion.add(elemento);
    }

    public void eliminar(String id) {
        Iterator<T> iterator = coleccion.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getIdentificador().equals(id)) {
                iterator.remove();
                return;
            }
        }
    }

    public T buscarPorGenero(String genero) {
        for (T media : coleccion) {
            if (media.getGenero().equals(genero)) {
                return media;
            }
        }
        return null;
    }

    public List<T> obtenerTodos() {
        List<T> listaOrdenada = new ArrayList<>(coleccion);
        listaOrdenada.sort(new Comparator<T>() {
            @Override
            public int compare(T m1, T m2) {
                return m1.getTitulo().compareTo(m2.getTitulo());
            }
        });
        return listaOrdenada;
    }

    public boolean actualizarAtributo(String id, String atributo, String nuevoValor) {
        for (T media : coleccion) {
            if (media.getIdentificador().equals(id)) {
                switch (atributo.toLowerCase()) {
                    case "titulo":
                        media.setTitulo(nuevoValor);
                        return true;
                    case "creador":
                        media.setCreador(nuevoValor);
                        return true;
                    case "genero":
                        media.setGenero(nuevoValor);
                        return true;
                    default:
                        System.out.println("Atributo no válido.");
                        return false;
                }
            }
        }
        System.out.println("ID no encontrado.");
        return false;
    }
}

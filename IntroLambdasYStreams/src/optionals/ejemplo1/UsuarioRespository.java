package optionals.ejemplo1;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioRespository {
    private static final List<Usuario> usuarios = Arrays.asList(
            new Usuario("Juan"),
            new Usuario("Ana"),
            new Usuario("Carlos")
    );

    public static Optional<Usuario> buscarPorNombre(String nombre) {
        return usuarios.stream()
                .filter(u -> u.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
}

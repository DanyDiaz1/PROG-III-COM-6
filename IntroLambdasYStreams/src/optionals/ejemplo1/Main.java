package optionals.ejemplo1;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Usuario> usuario = UsuarioRespository.buscarPorNombre("Ana");
        usuario.ifPresent(u -> System.out.println("Usuario encontrado: " + u.getNombre()));

        Optional<Usuario> usuarioNoExiste = UsuarioRespository.buscarPorNombre("Pedro");
        System.out.println("Usuario encontrado: " + usuarioNoExiste.orElse(new Usuario("Invitado")).getNombre());
    }
}

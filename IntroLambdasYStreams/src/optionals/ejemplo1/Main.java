package optionals.ejemplo1;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Usuario> usuario = UsuarioRespository.buscarPorNombre("Ana");

        //usuario.ifPresent(u -> System.out.println("Usuario encontrado: " + u.getNombre()));
        if(usuario.isPresent()){
            System.out.println(usuario.get().getNombre());
        }else{
            System.out.println("El usuario no se encontro");
        }
        Optional<Usuario> usuarioNoExiste = UsuarioRespository.buscarPorNombre("Carlos");
        System.out.println("Usuario encontrado: " + usuarioNoExiste.orElse(new Usuario("Invitado")).getNombre());
    }
}

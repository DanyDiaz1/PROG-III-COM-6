@FunctionalInterface
public interface Operacion {
    int calcular(int a, int b);

    default void mostrarMensaje(){
        System.out.println("Hola soy un metodo default");
    }

    static void mostrarMensaje1(){
        System.out.println("Hola soy un metodo estatico");
    }

}

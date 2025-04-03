import java.util.function.Function;

public class App {
    public static void main(String[] args) {
        Operacion suma = (a,b)-> a*b;

        System.out.println(suma.calcular(5,3));

        Function<String,Integer> longitud = (str)->{return str.length();};

        System.out.println(longitud.apply("Hola Mundo"));

        //Operacion multiplicar = (a,b)-> a*b;

        //System.out.println(multiplicar.calcular(5,5));
    }

}

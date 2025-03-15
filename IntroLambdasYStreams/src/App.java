public class App {
    public static void main(String[] args) {
        Operacion suma = Integer::sum;

        Operacion multiplicar = (a,b)->{
            return a*b;
        };

        System.out.println(multiplicar.calcular(5,5));
    }

}

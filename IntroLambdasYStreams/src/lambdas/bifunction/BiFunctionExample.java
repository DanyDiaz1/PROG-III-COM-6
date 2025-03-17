package lambdas.bifunction;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        /*
         * ✨ BiFunction
         * 💡 Recibe dos valores y retorna un resultado.
         */

        BiFunction<Integer,Integer,Integer> biFunction =(num1,num2)->{return num1*num2;};
        System.out.println(biFunction.apply(3,5));

    }
}

package lambdas.binary;

import java.util.function.BinaryOperator;

public class BinaryOperatorExample {
    public static void main(String[] args) {
        /*
         * 🧮 BinaryOperator
         * 💡 Recibe dos valores del mismo tipo y retorna un valor del mismo tipo.
         */
        BinaryOperator<Integer> binaryOp = Integer::sum;
        int result=binaryOp.apply(5,5);
        System.out.println(result);
    }
}

package biconsumer;

import java.util.function.BiConsumer;

public class BiConsumerExample {
    public static void main(String[] args) {
        /*
         * ⚙️ BiConsumer
         * 💡 Recibe dos valores y no retorna nada.
         */

        BiConsumer<String,String> biConsumer = (p1,p2)-> System.out.println(p1 + " " + p2);
        biConsumer.accept("Hola","Mundo");

    }
}

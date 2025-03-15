package bipredicate;

import java.util.function.BiPredicate;

public class BiPredicateExample {
    public static void main(String[] args) {
        /*
         * 🔍 BiPredicate
         * 💡 Recibe dos valores y devuelve un booleano (true o false).
         */

        BiPredicate<String,String> biPredicate = (p1,p2)->{return p1.length()>p2.length();};
        System.out.println(biPredicate.test("holaaaaaa","que tal"));
        System.out.println(biPredicate.test("hola","que tal"));
    }
}

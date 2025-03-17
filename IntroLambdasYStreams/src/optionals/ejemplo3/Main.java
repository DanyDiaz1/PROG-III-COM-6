package optionals.ejemplo3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(3, 7, 9, 12, 15, 18);

        Optional<Integer> primerPar = numeros.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();

        System.out.println("Primer número par encontrado: " + primerPar.orElse(-1));
    }
}

package tech.ada.queroserdev.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MatriculaUtils {

    private static Set<Integer> matriculasGeradas = new HashSet<>();
    private static Random r = new Random();

    public static int gerarMatriculaUnica() {
        int matricula;
        do {
            matricula = r.nextInt(1000000);
        } while (matriculasGeradas.contains(matricula));
        matriculasGeradas.add(matricula);
        return matricula;
    }
}
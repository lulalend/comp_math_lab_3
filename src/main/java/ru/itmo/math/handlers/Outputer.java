package ru.itmo.math.handlers;

import ru.itmo.math.entity.Equation;

public class Outputer {

    public static void outputResult(Equation equation, double result, double a, double b, double eps, int n) {
        int accuracy = (int) -Math.log10(eps);

        System.out.printf("%n%.2f%n    ∫(%s)dx = %." + accuracy + "f, число разбиений = %d%n%.2f",
                b, equation.getName(), result, n, a);
    }
}

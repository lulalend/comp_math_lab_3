package ru.itmo.math;

import ru.itmo.math.entity.Equation;
import ru.itmo.math.handlers.ConsoleReader;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Equation> equations = new TreeMap<>();

        Equation firstEquation = new Equation() {
            @Override
            public String getName() {
                return "x\u00B2";
            }

            @Override
            public double calculate(double x) {
                return x*x;
            }
        };

        Equation secondEquation = new Equation() {
            @Override
            public String getName() {
                return "|cos(x)-x\u00B3|-4x";
            }

            @Override
            public double calculate(double x) {
                return Math.abs(Math.cos(x) - x*x*x) - 4*x;
            }
        };


        Equation thirdEquation = new Equation() {
            @Override
            public String getName() {
                return "2x\u00B3-2x\u00B2+7x-14";
            }

            @Override
            public double calculate(double x) {
                return 2*x*x*x - 2*x*x + 7*x - 14;
            }
        };

        equations.put(1, firstEquation);
        equations.put(2, secondEquation);
        equations.put(3, thirdEquation);

        new ConsoleReader(scanner, equations).readFromConsole();

    }
}
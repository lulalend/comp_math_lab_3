package ru.itmo.math.handlers;

import ru.itmo.math.entity.Equation;
import ru.itmo.math.methods.*;

import java.util.Map;
import java.util.Scanner;

public class ConsoleReader {
    private final Scanner scanner;
    private final Map<Integer, Equation> equations;
    private Painter painter;

    public ConsoleReader(Scanner scanner, Map<Integer, Equation> equations) {
        this.scanner = scanner;
        this.equations = equations;
    }

    public void readFromConsole() {
        int equationNumber = chooseEquation(), n = 4;
        boolean flag = true;

        try {
            this.painter = new Painter(equationNumber);
            painter.show();
        } catch (NullPointerException e) {
            flag = false;
        }

        double a = getBoundary("левую"), b = getBoundary("правую");
        while (a >= b) {
            System.out.println("Левая граница должна быть строго меньше правой. Повторите ввод :)");
            a = getBoundary("левую"); b = getBoundary("правую");
        }

        int eps = getAccuracy();
        IntegrationMethod method = chooseIntegrationMethod();
        method.root(equations.get(equationNumber), a, b, n, Math.pow(10, -eps));

        if (flag) closePainter();
    }

    private int chooseEquation() {
        int equationNumber;

        System.out.println("Есть вот такие уравнения:");

        for (int key : equations.keySet()) {
            System.out.printf("(%d) " + equations.get(key).getName() + "%n", key);
        }

        while (true) {
            System.out.println("Какое выбираем? :)");
            String line = scanner.next();

            if (isNumber(line)) {
                equationNumber = Integer.parseInt(line);
                if (equationNumber >= 1 && equationNumber <= equations.size()) {
                    break;
                } else {
                    System.out.printf("Нужно ввести число, из диапазона от 1 до %d!%n", equations.size());
                }
            } else {
                System.out.println("Нужно ввести целое число!");
            }
        }

        return  equationNumber;
    }

    private double getBoundary(String str) {
        double boundary;

        while (true) {
            System.out.printf("Введите %s границу интервала:%n", str);
            try {
                boundary = Double.parseDouble(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Дробные числа записывать через точку...");
            }
        }

        return boundary;
    }

    private int getAccuracy() {
        int accuracy;

        while (true) {
            System.out.println("Введите точность вычисления (кол-во знаков):");
            try {
                accuracy = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число");
            }
        }

        return accuracy;
    }

    private IntegrationMethod chooseIntegrationMethod() {

        System.out.println("Есть методы: метод левых прямоугольников(1), метод правых прямоугольников(2), " +
                "метод средних(3), метод трапеций(4), метод Симпсона(5)");
        IntegrationMethod integrationMethod;

        while (true) {
            System.out.println("Какое выбираем? :)");
            String line = scanner.next();

            if ("1".equals(line)) {
                integrationMethod = new LeftRectanglesIntegrationMethod();
                break;
            } else if ("2".equals(line)) {
                integrationMethod = new RightRectanglesIntegrationMethod();
                break;
            } else if ("3".equals(line)) {
                integrationMethod = new AverageRectanglesIntegrationMethod();
                break;
            } else if ("4".equals(line)) {
                integrationMethod = new TrapezoidIntegrationMethod();
                break;
            } else if ("5".equals(line)) {
                integrationMethod = new SimpsonIntegrationMethod();
                break;
            }

            System.out.println("Необходимо ввести 1, 2, 3, 4 или 5!");
        }

        return integrationMethod;
    }

    private boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void closePainter() {
        System.out.println();
        System.out.println("Нажмите что-то, чтобы закончить программу");
        scanner.next();
        painter.close();
    }
}

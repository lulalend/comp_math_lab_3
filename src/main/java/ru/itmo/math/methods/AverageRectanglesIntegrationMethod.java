package ru.itmo.math.methods;

import ru.itmo.math.entity.Equation;
import ru.itmo.math.handlers.AnswerChecker;
import ru.itmo.math.handlers.Outputer;

public class AverageRectanglesIntegrationMethod implements IntegrationMethod {
    @Override
    public void root(Equation equation, double a, double b, int n, double eps) {
        double answer, previousAnswer = 0, h, sum = 0;

        h = (b - a) / n;
        for (double i = a; i < b; i += h) {
            sum += equation.calculate(i + h / 2);
        }
        sum *= h;
        answer = sum;

        while (!AnswerChecker.checkAnswer(answer, previousAnswer, eps)) {
            n *= 2;
            h = (b - a) / n;
            sum = 0;

            for (double i = a; i < b; i += h) {
                sum += equation.calculate(i + h / 2);
            }
            sum *= h;
            previousAnswer = answer;
            answer = sum;
        }

        Outputer.outputResult(equation, sum, a, b, eps, n);
    }
}


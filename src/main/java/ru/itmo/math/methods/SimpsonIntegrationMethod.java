package ru.itmo.math.methods;

import ru.itmo.math.entity.Equation;
import ru.itmo.math.handlers.AnswerChecker;
import ru.itmo.math.handlers.Outputer;

public class SimpsonIntegrationMethod implements IntegrationMethod {

    @Override
    public void root(Equation equation, double a, double b, int n, double eps) {
        double answer, previousAnswer = 0, h, sum, evenSum = 0, oddSum = 0;

        h = (b - a)/n;
        for (double i = a+2*h; i < b; i += 2*h) {
            evenSum += equation.calculate(i);
        }
        evenSum *= 4;

        for (double i = a+h; i < b; i += 2*h) {
            oddSum += equation.calculate(i);
        }
        oddSum *= 2;

        sum = equation.calculate(a) + equation.calculate(b) + evenSum + oddSum;
        sum *= h/3;
        answer = sum;

        while (!AnswerChecker.checkAnswer(answer, previousAnswer, eps)) {
            n *= 2;
            h = (b - a) / n;
            evenSum = 0; oddSum = 0;

            for (double i = a+h; i < b; i += 2*h) {
                evenSum += equation.calculate(i);
            }
            evenSum *= 4;

            for (double i = a+h; i < b; i += 2*h) {
                oddSum += equation.calculate(i);
            }
            oddSum *= 2;

            sum = equation.calculate(a) + equation.calculate(b) + evenSum + oddSum;
            sum *= h/3;
            previousAnswer = answer;
            answer = sum;
        }

        Outputer.outputResult(equation, sum, a, b, eps, n);
    }
}

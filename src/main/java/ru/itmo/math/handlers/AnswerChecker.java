package ru.itmo.math.handlers;

public class AnswerChecker {

    public static boolean checkAnswer(double answer, double previousAnswer, double eps) {
        return eps > Math.abs(answer - previousAnswer);
    }

}

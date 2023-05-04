package ru.itmo.math.methods;

import ru.itmo.math.entity.Equation;

public interface IntegrationMethod {
    void root(Equation equation, double a, double b, int n, double eps);
}

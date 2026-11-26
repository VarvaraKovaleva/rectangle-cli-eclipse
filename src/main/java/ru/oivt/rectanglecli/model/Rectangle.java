package ru.oivt.rectanglecli.model;

/**
 * Класс модели, хранящий параметры прямоугольника и вычисляющий его характеристики.
 */
public class Rectangle implements Shape {
    private final double a;
    private final double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() { return a; }
    public double getB() { return b; }

    @Override
    public String getName() { return "Прямоугольник"; }

    @Override
    public double area() { return a * b; }

    @Override
    public double perimeter() { return 2 * (a + b); }

    public double diagonal() { return Math.sqrt(a * a + b * b); }
    public boolean isSquare() { return Math.abs(a - b) < 1e-9; }
    public double inscribedCircleRadius() {
        if (!isSquare()) throw new IllegalStateException("Не квадрат");
        return a / 2;
    }
}
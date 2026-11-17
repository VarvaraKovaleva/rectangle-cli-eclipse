package ru.oivt.rectanglecli.model;

/**
 * Класс модели, хранящий параметры прямоугольника и вычисляющий его характеристики.
 */
public class Rectangle {
    private final double a; // длина
    private final double b; // ширина

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    // Геттеры (чтобы UI мог выводить значения)
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    /**
     * Периметр: P = 2(a + b)
     */
    public double perimeter() {
        return 2 * (a + b);
    }

    /**
     * Площадь: S = a * b
     */
    public double area() {
        return a * b;
    }

    /**
     * Диагональ: d = sqrt(a^2 + b^2)
     */
    public double diagonal() {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * Радиус вписанной окружности (только для квадрата): r = a / 2
     */
    public double inscribedCircleRadius() {
        if (!isSquare()) {
            throw new IllegalStateException("Вписанная окружность существует только для квадрата");
        }
        return a / 2;
    }

    /**
     * Проверка, является ли фигура квадратом (с учетом погрешности double)
     */
    public boolean isSquare() {
        // Сравнивать double через == нельзя, используем допуск
        return Math.abs(a - b) < 1e-9;
    }
}
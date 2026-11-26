package ru.oivt.rectanglecli.model;

public class Triangle implements Shape {
    private final double side;

    public Triangle(double side) {
        this.side = side;
    }

    public double getSide() { return side; }

    @Override
    public String getName() { return "Треугольник (равносторонний)"; }

    @Override
    public double area() {
        // Площадь равностороннего: (√3 / 4) * a²
        return (Math.sqrt(3) / 4) * side * side;
    }

    @Override
    public double perimeter() { return 3 * side; }
}
